package priv.hcx.sender.msg.decoder.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class Hex2StringConfigBean {
	private String id=CommonTools.createRandomID();
	private String name=null;
	private String encoding="GBK";
	private String type="encoder";
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
