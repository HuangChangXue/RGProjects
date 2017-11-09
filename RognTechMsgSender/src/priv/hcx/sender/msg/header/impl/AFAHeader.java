package priv.hcx.sender.msg.header.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import priv.hcx.sender.msg.header.HeaderEditor;

public class AFAHeader implements HeaderEditor {



	@Override
	public JFrame getEditorByName(String name) {
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

}
