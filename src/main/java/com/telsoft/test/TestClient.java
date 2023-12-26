/**
 * $Id: TestClient.java,v 1.4 2006/02/17 18:14:54 wuttke Exp $
 * Created on 08.04.2005
 * @author Matthias Wuttke
 * @version $Revision: 1.4 $
 */
package com.telsoft.test;

import com.telsoft.packet.AccessRequest;
import com.telsoft.packet.RadiusPacket;
import com.telsoft.util.RadiusClient;

/**
 * Simple Radius command-line client.
 */
public class TestClient {
	
	/**
	 * Radius command line client.
	 *
	 * Usage: TestClient <i>hostName sharedSecret userName password</i>
	 * @param args arguments
	 * @throws Exception
	 */
	public static void main(String[] args) 
	throws Exception {
		String host = "127.0.0.1";
		String shared = "testing123";
		String user = "mw";
		String pass = "test";
		
		RadiusClient rc = new RadiusClient(host, shared);

		// 1. Send Access-Request
		AccessRequest ar = new AccessRequest(user, pass);
		ar.setAuthProtocol(AccessRequest.AUTH_PAP); // or AUTH_CHAP
		ar.addAttribute("NAS-Identifier", "this.is.my.nas-identifier.de");
		System.out.println("Packet after it was sent\n" + ar + "\n");
		RadiusPacket response = rc.authenticate(ar);
		System.out.println("Response\n" + response + "\n");


//		RadiusPacket request = new HeartBeat(new StringAttribute(ParamContant.Transaction_Id, "Welcome Transaction-Id1"));
////		RadiusPacket request = new HeartBeat( new RadiusAttribute(ParamContant.Transaction_Id, RadiusUtil.getUtf8Bytes("Welcome Transaction-Id2")));
//
//		RadiusPacket response = rc.commonProcess(request);
//		System.out.println(response);
		rc.close();
	}
	
}
