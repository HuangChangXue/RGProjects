package priv.hcx.sender.tool;

import java.util.List;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.server.Server;
import priv.hcx.sender.server.ServerConf;

public class TransHelper {
	public static Message sendMessage(String serverConf,Message msg){
		ServerConf conf=Server.getServerConfig(serverConf);
		String protel=conf.getProtel();
		
		return null;
	}
		

}
