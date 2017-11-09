package priv.hcx.sender.msg.field.editor;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public interface FieldEditor {

	String getEditorName();
	String getFieldConfName();
	JPanel getEditPaneByFieldId(String fieldId);
	<T> T getFieldValue(T valueType,String fieldId);
	Map<String,Object> getCombiedFieldsValues(List<String> fieldId);
	boolean isAviableforName(String name);
}
