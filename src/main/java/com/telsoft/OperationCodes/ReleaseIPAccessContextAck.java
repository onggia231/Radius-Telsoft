package com.telsoft.OperationCodes;

import com.telsoft.attribute.StringAttribute;
import com.telsoft.packet.RadiusPacket;

public class ReleaseIPAccessContextAck extends RadiusPacket {
  public ReleaseIPAccessContextAck(StringAttribute...attributes) {
    super(RELEASE_IP_ACCESS_CONTEXT_ACK, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    ReleaseIPAccessContextAck attribute = new ReleaseIPAccessContextAck(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.Error_Code, "Value2"));
    System.out.println(attribute);
  }
}
