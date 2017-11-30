package priv.hcx.sender.protel;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.server.ServerConf;

public interface ProtelHandler {
	public String getHandlerName();
	public boolean canHandProtel(String name);
	public Message doSendMessage(ServerConf serverConf,Message msg, Object ...  args);
}
