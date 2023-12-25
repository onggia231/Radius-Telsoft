package org.tinyradius.OperationCodes;

import org.tinyradius.attribute.StringAttribute;
import org.tinyradius.packet.RadiusPacket;

public class IPAccessContextRequest extends RadiusPacket {

  public IPAccessContextRequest(StringAttribute ...attributes) {
    super(IP_ACCESS_CONTEXT_REQUEST, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    IPAccessContextRequest attribute = new IPAccessContextRequest(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.IP_Address, "Value2"),
        new StringAttribute(ParamContant.Error_Code, "Value3"));
    System.out.println(attribute);
  }

}
