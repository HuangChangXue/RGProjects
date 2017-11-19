package priv.hcx.sender.msg.field.editor.impl.bean;

import priv.hcx.sender.tool.CommonTools;

public class RandomConfigBean {
	private String id=CommonTools.createRandomID();
	private boolean containNum=true;
	private boolean containCharCap=false;
	private boolean containCharLow=false;
	private boolean containCharOther=false;
	private String  numLen,charCapLen,charLowLen,charOtherLen;
	private String  fieldID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isContainNum() {
		return containNum;
	}
	public void setContainNum(boolean containNum) {
		this.containNum = containNum;
	}
	public boolean isContainCharCap() {
		return containCharCap;
	}
	public void setContainCharCap(boolean containCharCap) {
		this.containCharCap = containCharCap;
	}
	public boolean isContainCharLow() {
		return containCharLow;
	}
	public void setContainCharLow(boolean containCharLow) {
		this.containCharLow = containCharLow;
	}
	public boolean isContainCharOther() {
		return containCharOther;
	}
	public void setContainCharOther(boolean containCharOther) {
		this.containCharOther = containCharOther;
	}
	public String getNumLen() {
		return numLen;
	}
	public void setNumLen(String numLen) {
		this.numLen = numLen;
	}
	public String getCharCapLen() {
		return charCapLen;
	}
	public void setCharCapLen(String charCapLen) {
		this.charCapLen = charCapLen;
	}
	public String getCharLowLen() {
		return charLowLen;
	}
	public void setCharLowLen(String charLowLen) {
		this.charLowLen = charLowLen;
	}
	public String getCharOtherLen() {
		return charOtherLen;
	}
	public void setCharOtherLen(String charOtherLen) {
		this.charOtherLen = charOtherLen;
	}
	public String getFieldID() {
		return fieldID;
	}
	public void setFieldID(String fieldID) {
		this.fieldID = fieldID;
	}
	
}
