package priv.hcx.sender.msg.decoder;

import priv.hcx.sender.bean.msg.Message;

public interface MsgDecoder {
	String getDecoderName();
	Message decodeMsg(byte[] data);
	String  decodeMsgForDisplay(Message msg);
	String  decodeMsgFormatedForDisplay(Message msg);
//	public String getEncoderName();
//
//	public byte[] encodeMsg(Message msg);
//	public String encodeMsgForDisplay(Message msg);
//	public String encodeMsgFormatedForDisplay(Message msg);
}
