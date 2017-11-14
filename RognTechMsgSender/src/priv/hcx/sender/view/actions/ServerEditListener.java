package priv.hcx.sender.view.actions;

import java.util.List;

import priv.hcx.sender.server.Server;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.server.ui.ServerConfUI;

public class ServerEditListener implements UnRecognizedCmdListener{

	@Override
	public boolean isRecognized(String cmd) {
		List<ServerConf> servers=Server.getAllServerConf();
		boolean rec=false;
		for(ServerConf server:servers){
			if(server.getName().equals(cmd)){
				rec=true;break;
			}
		}
		return rec;
	}

	@Override
	public Object doAction(String cmd) {
		ServerConfUI.loadConfig(cmd);
		return null;
	}

}
