package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ui.ConstFieldEditor;

public class ConstFieldProvider implements FieldEditor {
	private String name="ConstFieldProvider";
	@Override
	public String getEditorName() {
		return name;
	}
	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equalsIgnoreCase(this.name);
	}
	@Override
	public String getFieldConfName() {
		
		return null;
	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId) {
		return new ConstFieldEditor();
	}

	@Override
	public <T> T getFieldValue(T valueType, String fieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getCombiedFieldsValues(List<String> fieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
