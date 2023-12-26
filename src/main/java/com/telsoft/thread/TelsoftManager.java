package com.telsoft.thread;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Hashtable;
import org.apache.log4j.PropertyConfigurator;
import smartlib.admin.server.AppAuthenticator;
import smartlib.admin.server.DataHistoryUtil;
import smartlib.dictionary.Dictionary;
import smartlib.thread.FileThreadLister2;
import smartlib.thread.ProcessorListener;
import smartlib.thread.ThreadManager;
import smartlib.thread.ThreadProcessor;
import smartlib.util.Cache;
import smartlib.util.Global;
import smartlib.util.LogOutputStream;
import smartlib.util.StringUtil;


public class TelsoftManager implements ProcessorListener {
    private final String PRIV_KEY = "PRIV_KEY";
    private final String PUB_KEY = "PUB_KEY";
    private final Hashtable<String, String> key = new Hashtable<>();
    private Dictionary dic = null;

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("configuration/log4j.properties");
        TelsoftManager tsManager = new TelsoftManager();
        try {
            tsManager.initSystem();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public String getParameter(String strKey, String strDefault) {
        String res = dic.getString(strKey);
        if (res == null || res.equals("")) {
            res = strDefault;
        }
        return res;
    }


    public String getParameter(String strKey) {
        return getParameter(strKey, "");
    }


    public Connection getConnection() throws Exception {
        return null;
    }

    public void onCreate(ThreadProcessor processor) {
        processor.log = new DataHistoryUtil();
        processor.authenticator = new AppAuthenticator();
    }

    public void onOpen(ThreadProcessor processor) throws Exception {
        processor.mcnMain = getConnection();
        ((DataHistoryUtil) processor.log).setConnection(processor.mcnMain);
        ((AppAuthenticator) processor.authenticator).setConnection(processor.mcnMain);
    }

    public void initSystem() throws Exception {
        dic = new Dictionary("configuration/server.txt");

        try {
            Cache.CACHE_DURATION = Long.parseLong(getParameter("CacheDuration")) * 1000L;
        } catch (Exception ignored) {
        }

        try {
            Cache.CHECK_INTERVAL = Long.parseLong(getParameter("CheckInterval")) * 1000L;
        } catch (Exception ignored) {
        }

        String strLogFile = getParameter("ErrorLog", "error.log");
        String strWorkingDir = System.getProperty("user.dir");
        if (!strWorkingDir.endsWith("/") || !strWorkingDir.endsWith("\\")) {
            strWorkingDir += "/";
        }
        File fl = new File(strWorkingDir + strLogFile);
        if (fl.getParentFile() != null) {
            fl.getParentFile().mkdirs();
        }
        PrintStream ps = new PrintStream(new LogOutputStream(strWorkingDir + strLogFile));
        System.setOut(ps);
        System.setErr(ps);

        Global.APP_NAME = "Telsoft ThreadManager";

        int iPortID = 8338;
        try {
            iPortID = Integer.parseInt(getParameter("PortID"));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        ThreadManager cs = new ThreadManager(iPortID, this) {
            @Override
            public void close() {
                super.close();
                TelsoftManager.this.close();
            }
        };

        // Set action log file
        strLogFile = getParameter("ActionLog");
        fl = new File(strLogFile);
        if (fl.getParentFile() != null) {
            fl.getParentFile().mkdirs();
        }
        if (!strLogFile.equals("")) {
            cs.setActionLogFile(strLogFile);
        }
        // Set action log file
        strLogFile = getParameter("AlertLog");
        fl = new File(strLogFile);
        if (fl.getParentFile() != null) {
            fl.getParentFile().mkdirs();
        }
        if (!strLogFile.equals("")) {
            cs.setAlertLogFile(new File(strLogFile));
        }
        // Set max logfile size
        try {
            if (!getParameter("MaxLoggingSize").equals("")) {
                int iMaxLogFileSize = Integer.parseInt(getParameter("MaxLoggingSize"));
                if (iMaxLogFileSize > 0) {
                    cs.setMaxLogFileSize(iMaxLogFileSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set MaxLogContentSize
        try {
            if (!getParameter("MaxLogContentSize").equals("")) {
                int iMaxLogContentSize = Integer.parseInt(getParameter("MaxLogContentSize"));
                if (iMaxLogContentSize > 0) {
                    cs.setMaxLogContentSize(iMaxLogContentSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set max connection
        try {
            int iMaxConnectionAllowed = Integer.parseInt(getParameter("MaxConnectionAllowed"));
            if (iMaxConnectionAllowed > 0) {
                cs.setMaxConnectionAllowed(iMaxConnectionAllowed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = StringUtil.nvl(getParameter("PrivKey"), "");
        String temp = new String(Files.readAllBytes(Paths.get(path)));
        key.put(PRIV_KEY, temp);
        path = StringUtil.nvl(getParameter("PubKey"), "");
        temp = new String(Files.readAllBytes(Paths.get(path)));
        key.put(PUB_KEY, temp);
        cs.getThreadListers().clear();
        cs.getThreadListers().add(new FileThreadLister2("configuration/thread/"));
        // Start manager
        cs.start();
    }

    private void close() {
    }

    public String getPrivKey() {
        return key.get(PRIV_KEY);
    }

    public String getPubKey() {
        return key.get(PUB_KEY);
    }
}
