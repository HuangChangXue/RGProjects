package priv.hcx.sender.bean.msg;

import java.util.List;

import priv.hcx.sender.bean.MsgField;

public class MsgBody {
	List<MsgField> fields=null;

	public List<MsgField> getFields() {
		return fields;
	}

	public void setFields(List<MsgField> fields) {
		this.fields = fields;
	}
	private byte[] data=null;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}
