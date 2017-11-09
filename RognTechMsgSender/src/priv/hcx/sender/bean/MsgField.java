package priv.hcx.sender.bean;

import priv.hcx.sender.tool.CommonTools;

public class MsgField {
	private String name;
	private String desc;
	private String src;
	private String id;
	private String transactionid;
	private boolean isValueSet = false;

	public MsgField(String transactionid) {
		this.id = CommonTools.createRandomID();
		this.desc = "描述";
		this.src = CommonTools.loadService(priv.hcx.sender.msg.field.editor.FieldEditor.class).get(0).getEditorName();
		this.name = "新名称";
		this.transactionid = transactionid;
	}

	public MsgField() {
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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public boolean isValueSet() {
		return isValueSet;
	}

	public void setValueSet(boolean isValueSet) {
		this.isValueSet = isValueSet;
	}

}
