package priv.hcx.sender.msg.field.editor.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class FileConfigBean {
	private String id = CommonTools.createRandomID(), fieldID;
	private String groupId= CommonTools.createRandomID();
	private String fieldSep;
	private String fileName;
	private String fieldMapping;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldID() {
		return fieldID;
	}

	public void setFieldID(String fieldID) {
		this.fieldID = fieldID;
	}




	public String getFieldMapping() {
		return fieldMapping;
	}

	public void setFieldMapping(String fieldMapping) {
		this.fieldMapping = fieldMapping;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFieldSep() {
		return fieldSep;
	}

	public void setFieldSep(String fieldSep) {
		this.fieldSep = fieldSep;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
