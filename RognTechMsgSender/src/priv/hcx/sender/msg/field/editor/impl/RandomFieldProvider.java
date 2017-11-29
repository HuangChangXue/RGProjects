package priv.hcx.sender.msg.field.editor.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.RandomFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class RandomFieldProvider implements FieldEditor {
	private String name = "RandomFieldProvider";

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

	private static RandomFieldEditor inst = null;

	@Override
	public JPanel getEditPaneByFieldId(String fieldId,List<MsgField> fields) {
		if (inst == null) {
			inst = new RandomFieldEditor();
		}
		try {

			inst.setConfig(getConfigBean(fieldId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inst;
	}

	private RandomConfigBean getConfigBean(String fieldId) {
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

	@Override
	public  void configMsgField(MsgField msg) {
		msg.setValue(CommonTools.createRandomID());
	}

	@Override
	public void configcombiedFields(MsgField msg,List<MsgField> fields){
		msg.setValue(CommonTools.createRandomID());
	}

	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equals(this.name);
	}

}
