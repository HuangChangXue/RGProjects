package priv.hcx.sender.msg.field.editor.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class ConstConfigBean {
	private boolean isAllowEmpty, isAllowNewLine;
	private String value, id = CommonTools.createRandomID(), fieldID;

	public Boolean getIsAllowEmpty() {
		return isAllowEmpty;
	}

	public void setIsAllowEmpty(Boolean isAllowEmpty) {
		this.isAllowEmpty = isAllowEmpty;
	}

	public Boolean getIsAllowNewLine() {
		return isAllowNewLine;
	}

	public void setIsAllowNewLine(Boolean isAllowNewLine) {
		this.isAllowNewLine = isAllowNewLine;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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

}
