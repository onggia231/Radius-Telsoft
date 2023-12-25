package org.tinyradius.OperationCodes;

import org.tinyradius.attribute.RadiusAttribute;
import org.tinyradius.attribute.StringAttribute;
import org.tinyradius.packet.RadiusPacket;

public class HeartBeat extends RadiusPacket {

//  public HeartBeat() {
//    super(HEART_BEAT, getNextPacketIdentifier());
//    this.addAttribute(new StringAttribute(Transaction_Id, "Welcome Transaction-Id"));
//    this.addAttribute(new StringAttribute(IP_Addressing_Zone, "Welcome IP_Addressing_Zone"));
//    this.addAttribute(new RadiusAttribute(Transaction_Id, RadiusUtil.getUtf8Bytes("Welcome Transaction-Id")));
//    this.addAttribute("IP-Address", "Welcome IP-Address");
//  }

  public HeartBeat(StringAttribute ...attributes) {
    super(HEART_BEAT, getNextPacketIdentifier());
    for (StringAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public HeartBeat(RadiusAttribute...attributes) {
    super(HEART_BEAT, getNextPacketIdentifier());
    for (RadiusAttribute attribute : attributes) {
      this.addAttribute(attribute);
    }
  }

  public static void main(String[] args) {
    HeartBeat attribute = new HeartBeat(
        new StringAttribute(ParamContant.Transaction_Id, "Value1"));
    System.out.println(attribute);
  }

}
