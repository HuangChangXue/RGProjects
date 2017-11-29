package priv.hcx.sender.msg.encoder;

import priv.hcx.sender.bean.msg.Message;

public interface MsgEncoder {
	public String getEncoderName();

	public byte[] encodeMsg(Message msg);
	public String encodeMsgForDisplay(Message msg);
	public String encodeMsgFormatedForDisplay(Message msg);
}
