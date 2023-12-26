package com.telsoft.thread;

import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Vector;
import smartlib.database.batch.BatchStatement;
import smartlib.thread.DBManageableThread;
import smartlib.thread.ManageableThread;
import smartlib.thread.ParameterType;
import smartlib.thread.ParameterUtil;
import smartlib.util.AppException;

public class TServer extends DBManageableThread {

  String filePath, filePathBackup, errorPath, delimiter, wildCard;
  long batchSize;
  HashMap<String, Long> comMapping;
  private PreparedStatement pstmtCheckCompanyId = null, pstmtMbf30FileDtlIsdn = null;
  private BatchStatement bstmtMbf30File = null, bstmtMbf30FileDtl = null;

  @Override
  public Vector getParameterDefinition() {
    Vector vtTemp = new Vector<>();
    vtTemp.add(createParameter("FilePath", "", ParameterType.PARAM_TEXTBOX_MAX, "1000"));
    vtTemp.add(createParameter("FilePathBackup", "", ParameterType.PARAM_TEXTBOX_MAX, "1000"));
    vtTemp.add(createParameter("ErrorPath", "", ParameterType.PARAM_TEXTBOX_MAX, "1000"));
    vtTemp.add(createParameter("Delimiter", "", ParameterType.PARAM_TEXTBOX_MAX, "10"));
    vtTemp.add(createParameter("WildCard", "", ParameterType.PARAM_TEXTBOX_MAX, "100"));
    vtTemp.add(createParameter("BatchSize", "", ParameterType.PARAM_TEXTBOX_MASK, "99990"));
    Vector vtDef = new Vector();
    vtDef.add(ParameterUtil.createParameter("ShopCode", "", ParameterType.PARAM_TEXTBOX_MAX, "500", "", "1"));
    vtDef.add(ParameterUtil.createParameter("ComId", "", ParameterType.PARAM_TEXTBOX_MASK, "999999990", "", "2"));
    vtTemp.add(ManageableThread.createParameter("Mbf30-Setting", "", ParameterType.PARAM_TABLE, vtDef, "Mbf30 WebService mapping", "", "Mbf30"));
    vtTemp.addAll(super.getParameterDefinition());
    return vtTemp;
  }

  @Override
  public void fillParameter() throws AppException {
    filePath = loadDirectory("FilePath", true, true);
    filePathBackup = loadDirectory("FilePathBackup", true, true);
    errorPath = loadDirectory("ErrorPath", true, true);
    delimiter = loadString("Delimiter");
    wildCard = loadString("WildCard");
    batchSize = loadUnsignedLong("BatchSize");
    // Get data table thread
    comMapping = new HashMap<>();
    Object obj = getParameter("Mbf30-Setting");
    super.fillParameter();
  }

  @Override
  protected void beforeSession() throws Exception {

  }

  @Override
  protected void processSession() {

  }

  @Override
  protected void afterSession() throws Exception {

  }

  private void logMonitor(String log, Object... arguments) {
    logMonitor(MessageFormat.format(log, arguments));
  }

  private void importFile(String fileName) throws Exception {

  }

}
