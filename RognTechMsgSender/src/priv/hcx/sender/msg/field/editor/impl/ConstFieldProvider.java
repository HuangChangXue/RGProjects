package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.ConstFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class ConstFieldProvider implements FieldEditor {
	private String name = "ConstFieldProvider";

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

	private static ConstFieldEditor inst = null;

	@Override
	public JPanel getEditPaneByFieldId(String fieldId) {
		if (inst == null) {
			inst = new ConstFieldEditor();
		}
		try {
			List<ConstConfigBean> confs=CommonTools.doDBQueryOperation(ConstConfigDao.class, "queryByFieldId", ConstConfigBean.class, new Class[]{String.class}, fieldId);
			ConstConfigBean bean=null;
			if(confs.size()>0){
				bean=confs.get(0);
			}
			else {
				bean=new ConstConfigBean();
				bean.setFieldID(fieldId);
			}
			inst.setConfig(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inst;
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
