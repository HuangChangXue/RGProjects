package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ui.SequenceFieldEditor;

public class SequenceFieldProvider implements FieldEditor {

	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return name;
	}

	private String name = "SequenceFieldProvider";

	@Deprecated
	public String getFieldConfName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId,List<MsgField> fields) {
		// TODO Auto-generated method stub
		return new SequenceFieldEditor();
	}

	@Override
	public  void configMsgField(MsgField msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void configcombiedFields(MsgField msg,List<MsgField> fields){

	}

	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equals(this.name);
	}

}
