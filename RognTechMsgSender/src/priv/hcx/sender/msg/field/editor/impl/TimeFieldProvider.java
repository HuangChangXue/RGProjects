package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ui.TimeFieldEditor;

public class TimeFieldProvider implements FieldEditor {

	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return name;
	}

	private String name = "TimeFieldProvider";

	@Override
	public String getFieldConfName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId,List<MsgField> fields) {
		// TODO Auto-generated method stub
		return new TimeFieldEditor();
	}

	@Override
	public <T> T getFieldValue(Class<T> valueType, String fieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getCombiedFieldsValues(List<String> fieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equals(this.name);
	}

}
