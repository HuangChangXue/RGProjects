package priv.hcx.sender.msg.encoder;

import javax.swing.JDialog;

import priv.hcx.sender.bean.msg.Message;

public interface MsgEncoder {
	public String getEncoderName();

	public byte[] encodeMsg(Message msg);
	public String encodeMsgForDisplay(Message msg);
	public String encodeMsgFormatedForDisplay(Message msg);
	JDialog  editEncoderConfigDialog(String config);
	String getCurrentConfigName();
}
