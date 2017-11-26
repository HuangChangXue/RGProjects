package priv.hcx.sender.msg.field.editor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.FileFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class FileFieldProvider implements FieldEditor {
	private String name = "FileFieldProvider";

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
	private FileConfigBean getConfigBean(String fieldId) {
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
	private static FileFieldEditor inst=null;
	private List<FileConfigBean> getConfigBean(String fieldId) {
		List<FileConfigBean> confs = null;
		;
		try {
			confs = CommonTools.doDBQueryOperation(FileConfigDao.class, "queryByFieldId", FileConfigBean.class, new Class[] { String.class }, fieldId);

			if (confs==null||confs.size() <=0) {
				FileConfigBean bean = new FileConfigBean();
				bean.setFieldID(fieldId);
				confs=new ArrayList<FileConfigBean>();
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
			inst = new FileFieldEditor();
		}
		try {
			inst.setFields(fields);
			List<FileConfigBean>  confs=new ArrayList<FileConfigBean>();
			confs = CommonTools.doDBQueryOperation(FileConfigDao.class, "queryGroupByFieldId", FileConfigBean.class, new Class[] { String.class }, fieldId);
			if(confs.size()<=0){
				FileConfigBean bean=new FileConfigBean();
				bean.setFieldID(fieldId);
				confs.add(bean);
			}
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
