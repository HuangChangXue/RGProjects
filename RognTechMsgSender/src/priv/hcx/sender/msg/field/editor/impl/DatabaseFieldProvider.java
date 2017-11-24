package priv.hcx.sender.msg.field.editor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.ConstFieldEditor;
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
	private static DataBaseFieldEditor inst=null;
	private List<DataBaseConfigBean> getConfigBean(String fieldId) {
		List<DataBaseConfigBean> confs = null;
		;
		try {
			confs = CommonTools.doDBQueryOperation(DataBaseConfigDao.class, "queryByFieldId", DataBaseConfigBean.class, new Class[] { String.class }, fieldId);

			if (confs==null||confs.size() <=0) {
				DataBaseConfigBean bean = new DataBaseConfigBean();
				bean.setFieldID(fieldId);
				confs=new ArrayList<DataBaseConfigBean>();
				confs.add(bean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return confs;

	}
	@Override
	public JPanel getEditPaneByFieldId(String fieldId,List<MsgField> fields) {
		
		if (inst == null) {
			inst = new DataBaseFieldEditor();
		}
		try {
			inst.setFields(fields);
			List<DataBaseConfigBean>  confs=new ArrayList<DataBaseConfigBean>();
			confs = CommonTools.doDBQueryOperation(DataBaseConfigDao.class, "queryGroupByFieldId", DataBaseConfigBean.class, new Class[] { String.class }, fieldId);

			inst.setConfig(confs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inst;
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
