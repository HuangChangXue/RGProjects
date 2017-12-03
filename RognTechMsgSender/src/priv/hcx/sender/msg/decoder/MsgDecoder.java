package priv.hcx.sender.msg.decoder;

import javax.swing.JDialog;

import priv.hcx.sender.bean.msg.Message;

public interface MsgDecoder {
	String getDecoderName();
	Message decodeMsg(byte[] data);
	String  decodeMsgForDisplay(Message msg);
	String  decodeMsgFormatedForDisplay(Message msg);
	JDialog  editDecoderConfigDialog(String config);
	String getCurrentConfigName();
//	public String getEncoderName();
//
//	public byte[] encodeMsg(Message msg);
//	public String encodeMsgForDisplay(Message msg);
//	public String encodeMsgFormatedForDisplay(Message msg);
}
