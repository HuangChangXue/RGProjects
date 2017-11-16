package priv.hcx.sender.msg.decoder.impl;

import priv.hcx.sender.msg.decoder.MsgDecoder;

public class Hex2StringDecoder implements MsgDecoder {

	@Override
	public String getDecoderName() {
		return this.getClass().getName();
	}

}
