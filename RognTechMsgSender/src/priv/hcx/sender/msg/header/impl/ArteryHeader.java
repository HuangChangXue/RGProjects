package priv.hcx.sender.msg.header.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import priv.hcx.sender.msg.header.HeaderEditor;

public class ArteryHeader implements HeaderEditor {


	@Override
	public JFrame getEditorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return "ArteryHeader";
	}

	@Override
	public List<String> getConfigList() {
		
			List ret=new ArrayList<String>();
			ret.add("AA");
			ret.add("BB");
			ret.add("CC");
			return ret;
	}


}
