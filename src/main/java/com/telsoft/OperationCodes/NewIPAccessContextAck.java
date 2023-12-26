package com.telsoft.OperationCodes;

import com.telsoft.attribute.StringAttribute;
import com.telsoft.packet.RadiusPacket;

public class NewIPAccessContextAck extends RadiusPacket {

  public NewIPAccessContextAck(StringAttribute... attributes) {
    super(NEW_IP_ACCESS_CONTEXT_ACK, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    NewIPAccessContextAck attribute = new NewIPAccessContextAck(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.Error_Code, "Value2"));
    System.out.println(attribute);
  }

}
