package org.tinyradius.OperationCodes;

import org.tinyradius.attribute.StringAttribute;
import org.tinyradius.packet.RadiusPacket;

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
