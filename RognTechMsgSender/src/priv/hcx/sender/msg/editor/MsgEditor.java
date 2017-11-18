package priv.hcx.sender.msg.editor;

import javax.swing.JPanel;

import priv.hcx.sender.bean.msg.Message;

public interface MsgEditor {

	// public JPanel getEditorPanel(String transactionName,String
	// configFileName);
	public JPanel getEditorPanel(String transactionName);

	public boolean saveAllMsgConfig();

	public boolean saveMsgConfig(String transactionName);

	public Message getMessageByTransactionId(String transactionId);
}
