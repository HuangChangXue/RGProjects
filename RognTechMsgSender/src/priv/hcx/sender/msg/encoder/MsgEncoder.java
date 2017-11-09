package priv.hcx.sender.msg.encoder;

public interface MsgEncoder {
public String getEncoderName();
public byte[] encodeMsg(String msg);
}
