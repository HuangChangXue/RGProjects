package priv.hcx.sender.msg.field.editor.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class DataBaseConfigBean {
	private String id = CommonTools.createRandomID(), fieldID;
	private String previewSql;
	private String dbconnection;
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

	public String getPreviewSql() {
		return previewSql;
	}

	public void setPreviewSql(String previewSql) {
		this.previewSql = previewSql;
	}

	public String getDbconnection() {
		return dbconnection;
	}

	public void setDbconnection(String dbconnection) {
		this.dbconnection = dbconnection;
	}

	public String getFieldMapping() {
		return fieldMapping;
	}

	public void setFieldMapping(String fieldMapping) {
		this.fieldMapping = fieldMapping;
	}

}
