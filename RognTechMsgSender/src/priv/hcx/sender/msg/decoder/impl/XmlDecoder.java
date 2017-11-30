package priv.hcx.sender.msg.decoder.impl;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.msg.decoder.MsgDecoder;

public class XmlDecoder implements MsgDecoder {
	@Override
	public String getDecoderName() {
		return "XmlDecoder";
	}

	@Override
	public Message decodeMsg(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeMsgForDisplay(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeMsgFormatedForDisplay(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}
}
