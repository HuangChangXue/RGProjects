package priv.hcx.sender.bean;

import priv.hcx.sender.tool.CommonTools;

public class Transaction {
	String id;
	String name;
	String desc;
	String folderid;
	String headerEditor;
	String headerEditorConfigName;
	public Transaction() {
	
	}

	public Transaction(String name) {
		this.name = name;
		this.id = CommonTools.createRandomID();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFolderid() {
		return folderid;
	}

	public void setFolderid(String folderid) {
		this.folderid = folderid;
	}

	public String getHeaderEditor() {
		return headerEditor;
	}

	public void setHeaderEditor(String headerEditor) {
		this.headerEditor = headerEditor;
	}

	public String getHeaderEditorConfigName() {
		return headerEditorConfigName;
	}

	public void setHeaderEditorConfigName(String headerEditorConfigName) {
		this.headerEditorConfigName = headerEditorConfigName;
	}

}
