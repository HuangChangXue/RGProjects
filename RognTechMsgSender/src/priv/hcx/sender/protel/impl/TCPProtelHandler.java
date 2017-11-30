package priv.hcx.sender.protel.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.protel.ProtelHandler;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.MessageHelper;

public class TCPProtelHandler implements ProtelHandler {
	private String name = "TCP/IP";

	@Override
	public String getHandlerName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean canHandProtel(String name) {
		// TODO Auto-generated method stub
		return this.name.equals(name);
	}

	@Override
	public Message doSendMessage(ServerConf serverConf, Message msg, Object... args) {

		String host = serverConf.getHost();
		String port = serverConf.getPort();
		MsgEncoder encoder = CommonTools.getEncoderByName(serverConf.getEncoder());
		if (encoder != null) {
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			byte[] data = encoder.encodeMsg(msg);
			Socket so = null;
			try {
				so = new Socket(host, Integer.valueOf(port));
				OutputStream os=so.getOutputStream();
				os.write(data);
				os.flush();
				so.shutdownOutput();
				InputStream is=so.getInputStream();
				byte[] tmp=new byte[512];
				int len=0;
				while((len= is.read(tmp))>0){
					bos.write(tmp, 0, len);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					so.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			MsgDecoder decoder=CommonTools.getDecoderByName(serverConf.getDecoder());
			return decoder.decodeMsg(bos.toByteArray());
		}
		return null;
	}

}
