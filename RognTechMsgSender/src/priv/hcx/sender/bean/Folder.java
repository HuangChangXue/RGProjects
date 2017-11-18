package priv.hcx.sender.bean;

import priv.hcx.sender.tool.CommonTools;

public class Folder {
	String id;
	String name;
	String desc;
	String parentid;

	public Folder() {
	}

	public Folder(String name) {
		this.name = name;

	}

	public void createRandomId() {
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
