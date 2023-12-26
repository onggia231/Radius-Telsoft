package com.telsoft.OperationCodes;

import com.telsoft.attribute.StringAttribute;
import com.telsoft.packet.RadiusPacket;

public class NewIPAccessContext extends RadiusPacket {

  public NewIPAccessContext(StringAttribute... attributes) {
    super(NEW_IP_ACCESS_CONTEXT, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    NewIPAccessContext attribute = new NewIPAccessContext(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.Calling_Line_Identification, "Value2"),
        new StringAttribute(ParamContant.Formatted_RemoteId, "Value3"),
        new StringAttribute(ParamContant.IP_Address, "Value4"),
        new StringAttribute(ParamContant.IP_Addressing_Zone, "Value5"),
        new StringAttribute(ParamContant.Terminal_Type, "Value6"));
    System.out.println(attribute);
  }

}
