package priv.hcx.sender.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.bean.res.MsgFieldDao;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.view.SenderMainFrame;

public class MessageHelper {
	private static Transaction trans = null;

	public static void setCurrentSelectedTransaction(Transaction trans) {
		MessageHelper.trans = trans;
	}

	public static Transaction getCurrentSelectedTransaction() {
		return trans;
	}

	public static String getSelectedMsgId() {
		if (trans != null) {
			return trans.getId();
		} else {
			
			trans= SenderMainFrame.getSelectedTransaction();
			return trans.getId();
		
		}
//		return null;
	}

	static Map<String, priv.hcx.sender.msg.field.editor.FieldEditor> editors = new HashMap<String, priv.hcx.sender.msg.field.editor.FieldEditor>();
	static {
		List<FieldEditor> editorsl = CommonTools.loadService(priv.hcx.sender.msg.field.editor.FieldEditor.class);
		for (FieldEditor editor : editorsl) {
			editors.put(editor.getEditorName(), editor);
		}

	}

	public static Message getMessage(String transId) {
		Message msg = new Message();
		List<MsgField> fields = null;
		MsgField field = new MsgField();
		field.setId(transId);
		try {
			fields = CommonTools.doDBQueryOperation(MsgFieldDao.class, "queryByTransactonId", MsgField.class, new Class[] { String.class }, transId);
			if (fields == null)
				return null;
			for (MsgField fiel : fields) {
				if (!fiel.isValueSet()) {
					FieldEditor editor = editors.get(fiel.getSrc());
					editor.configcombiedFields(fiel, fields);
				}
			}
			MsgHead header = new MsgHead();
			msg.setHead(header);
			MsgBody body = new MsgBody();
			body.setFields(fields);
			msg.setBody(body);
			MsgTail tail = new MsgTail();
			msg.setTail(tail);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
