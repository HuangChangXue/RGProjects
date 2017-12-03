package priv.hcx.sender.msg.decoder.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class XmlConfigBean {
	private String id = CommonTools.createRandomID(), 
			encoding = "UTF-8", 
			rootType = "AsTrans", 
			rootTagName, 
			fieldNameType = "AsTagName", 
			fieldNameTagName, 
			fielNameAttrName,
			fieldValueType = "AsText", 
			fieldValueAttrName,name,type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getRootType() {
		return rootType;
	}

	public void setRootType(String rootType) {
		this.rootType = rootType;
	}

	public String getRootTagName() {
		return rootTagName;
	}

	public void setRootTagName(String rootTagName) {
		this.rootTagName = rootTagName;
	}

	public String getFieldNameType() {
		return fieldNameType;
	}

	public void setFieldNameType(String fieldNameType) {
		this.fieldNameType = fieldNameType;
	}

	public String getFieldNameTagName() {
		return fieldNameTagName;
	}

	public void setFieldNameTagName(String fieldNameTagName) {
		this.fieldNameTagName = fieldNameTagName;
	}

	public String getFielNameAttrName() {
		return fielNameAttrName;
	}

	public void setFielNameAttrName(String fielNameAttrName) {
		this.fielNameAttrName = fielNameAttrName;
	}

	public String getFieldValueType() {
		return fieldValueType;
	}

	public void setFieldValueType(String fieldValueType) {
		this.fieldValueType = fieldValueType;
	}

	public String getFieldValueAttrName() {
		return fieldValueAttrName;
	}

	public void setFieldValueAttrName(String fieldValueAttrName) {
		this.fieldValueAttrName = fieldValueAttrName;
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
