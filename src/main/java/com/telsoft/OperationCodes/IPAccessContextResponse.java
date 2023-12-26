package com.telsoft.OperationCodes;

import com.telsoft.attribute.StringAttribute;
import com.telsoft.packet.RadiusPacket;

public class IPAccessContextResponse extends RadiusPacket {

  public IPAccessContextResponse(StringAttribute...attributes) {
    super(IP_ACCESS_CONTEXT_RESPONSE, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    IPAccessContextResponse attribute = new IPAccessContextResponse(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"),
        new StringAttribute(ParamContant.Network_Type, "Value2"),
        new StringAttribute(ParamContant.Calling_Line_Identification, "Value3"),
        new StringAttribute(ParamContant.Formatted_RemoteId, "Value4"),
        new StringAttribute(ParamContant.Terminal_Type, "Value5"),
        new StringAttribute(ParamContant.Error_Code, "Value6"));
    System.out.println(attribute);
  }

}
