package priv.hcx.sender.msg.header.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.msg.header.HeaderEditor;

public class AFAHeader implements HeaderEditor {



	@Override
	public JDialog getEditorFrameByName(String name) {
		// TODO Auto-generated method stub
		return null; 
	}








	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return "AFAHeader";
	}

	@Override
	public List<String> getConfigList() {
		List ret=new ArrayList<String>();
		ret.add("AAA");
		ret.add("ABB");
		ret.add("CAC");
		return ret;
	}

















	@Override
	public MsgHead createHead(String name,List<MsgField> fields) {
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public void saveConfig() {
		// TODO Auto-generated method stub
		
	}








	@Override
	public String getConfigIdByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}








	@Override
	public String getConfigNameByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
