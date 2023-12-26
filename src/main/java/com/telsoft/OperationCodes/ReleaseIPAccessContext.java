package com.telsoft.OperationCodes;

import com.telsoft.attribute.StringAttribute;
import com.telsoft.packet.RadiusPacket;

public class ReleaseIPAccessContext extends RadiusPacket {
  public ReleaseIPAccessContext(StringAttribute...attributes) {
    super(RELEASE_IP_ACCESS_CONTEXT, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    ReleaseIPAccessContext attribute = new ReleaseIPAccessContext(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.IP_Address, "Value2"),
        new StringAttribute(ParamContant.IP_Addressing_Zone, "Value3"));
    System.out.println(attribute);
  }
}
