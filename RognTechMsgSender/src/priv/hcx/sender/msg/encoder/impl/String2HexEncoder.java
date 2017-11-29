package priv.hcx.sender.msg.encoder.impl;

import java.util.List;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.tool.HEX2BIN;

public class String2HexEncoder implements MsgEncoder {

	@Override
	public String getEncoderName() {

		return "String2Hex";
	}


	@Override
	public byte[] encodeMsg(Message msg) {
		MsgHead head=msg.getHead();
		MsgBody body=msg.getBody();
		List<MsgField> fields=	body.getFields();
		MsgTail tail=msg.getTail();
		StringBuilder sb=new StringBuilder();
		for(int i =0;i<fields.size();++i){
			MsgField field=fields.get(i);
			sb.append(field.getName()).append("-").append(field.getValue()).append(";");
		}
		return sb.toString().getBytes();
	}

	@Override
	public String encodeMsgForDisplay(Message msg) {
		byte[] data=encodeMsg(msg);
		String ret=HEX2BIN.encode(data);
		return ret;
	}
	@Override
	public String encodeMsgFormatedForDisplay(Message msg) {
		String value=encodeMsgForDisplay(msg);
		StringBuilder sb=new StringBuilder();
		for( int i=0;i<value.length();i+=2){
			if(i%32==0){
				sb.append("\n");
			}
			sb.append(value.subSequence(i, i+2));
			sb.append(" ");
			
		}
		return  sb.toString();
	}

}
