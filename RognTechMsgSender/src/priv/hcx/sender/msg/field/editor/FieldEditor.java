package priv.hcx.sender.msg.field.editor;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;

public interface FieldEditor {

	/**
	 * 获取当前编辑器名称
	 * 
	 * @return
	 */
	String getEditorName();

	@Deprecated
	String getFieldConfName();

	/**
	 * 打开对应字段的编辑器
	 * 
	 * @param fieldId
	 * @return
	 */
	JPanel getEditPaneByFieldId(String fieldId,List<MsgField> fields);

	/**
	 * 获取对应字段的值
	 * 
	 * @param valueType
	 * @param fieldId
	 * @return
	 */
	<T> T getFieldValue(Class<T> valueType, String fieldId);

	/**
	 * 获取相关联的字段的值
	 * 
	 * @param fieldId
	 * @return
	 */
	Map<String, Object> getCombiedFieldsValues(List<String> fieldId);

	/**
	 * 检查是否支持指定名称的类型进行编辑
	 * 
	 * @param name
	 * @return
	 */
	boolean isAviableforName(String name);
}
