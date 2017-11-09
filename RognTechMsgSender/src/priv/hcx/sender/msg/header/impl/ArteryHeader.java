package priv.hcx.sender.msg.header.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.msg.header.HeaderEditor;
import priv.hcx.sender.msg.header.impl.afaheader.ArteryHeaderEditor;

public class ArteryHeader implements HeaderEditor {
	
	static Map<String ,ArteryHeaderEditor> editors=new HashMap<String,ArteryHeaderEditor>();
	@Override
	public JFrame getEditorFrameByName(String name) {
		if(editors.containsKey(name)){
			return editors.get(name);
		}
		else {
			ArteryHeaderEditor editor=new ArteryHeaderEditor(name);
			editors.put(name, editor);
			return editor;
		}
	}





	@Override
	public String getEditorName() {
		return "ArteryHeader";
	}

	@Override
	public List<String> getConfigList() {
		
			List ret=new ArrayList<String>();
	
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

