package priv.hcx.sender.view.actions;

import java.util.List;

import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.db.DataBase;
import priv.hcx.sender.db.ui.DataBaseConnectionEditorUI;
import priv.hcx.sender.server.ServerConf;

public class DataBaseEditListener implements UnRecognizedCmdListener {

	@Override
	public boolean isRecognized(String cmd) {
		List<DBConf> getAllDbConf = DataBase.getAllDbConf();
		boolean rec = false;
		for (DBConf server : getAllDbConf) {
			if (server.getName().equals(cmd)) {
				rec = true;
				break;
			}
		}
		return rec;
	}

	@Override
	public Object doAction(String cmd) {
		DataBaseConnectionEditorUI.loadConfig(cmd);
		return null;
	}

}
