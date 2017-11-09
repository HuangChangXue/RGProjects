package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import priv.hcx.sender.msg.field.editor.FieldEditor;

public class ConstFieldProvider implements FieldEditor {

	@Override
	public String getEditorName() {
		return "ConstFieldProvider";
	}

	@Override
	public String getFieldConfName() {
		
		return null;
	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId) {
		JPanel panel=new JPanel();
		panel.add(new JLabel("常量值:"));
		panel.add(new JTextField());
		return panel;
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

	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return true;
	}


}
