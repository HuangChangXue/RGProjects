package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ui.SequenceFieldEditor;

public class SequenceFieldProvider implements FieldEditor {

	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public
	String getFieldConfName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId) {
		// TODO Auto-generated method stub
		return new SequenceFieldEditor();
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
		return false;
	}

}
