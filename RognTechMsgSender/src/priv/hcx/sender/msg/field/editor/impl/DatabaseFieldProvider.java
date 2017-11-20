package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.DataBaseFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class DatabaseFieldProvider implements FieldEditor {
	private String name = "DatabaseFieldProvider";

	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getFieldConfName() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	private DataBaseConfigBean getConfigBean(String fieldId) {
		RandomConfigBean bean = null;
		try {
			List<RandomConfigBean> confs = CommonTools.doDBQueryOperation(RandomConfigDao.class, "queryByFieldId", RandomConfigBean.class, new Class[] { String.class }, fieldId);

			if (confs.size() > 0) {
				bean = confs.get(0);
			} else {
				bean = new RandomConfigBean();
				bean.setFieldID(fieldId);
			}
		} catch (Exception e) {

		}
		return bean;
	}
	*/
	@Override
	public JPanel getEditPaneByFieldId(String fieldId) {
		return new DataBaseFieldEditor();
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
