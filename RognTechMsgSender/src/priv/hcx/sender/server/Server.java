package priv.hcx.sender.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import priv.hcx.sender.server.dao.ServerConfDao;
import priv.hcx.sender.tool.CommonTools;

public class Server {
	public static List<ServerConf> getAllServerConf() {
		SqlSession session = CommonTools.getSQLSession(true);
		ServerConfDao dao = CommonTools.getMapper(session, ServerConfDao.class);
		List<ServerConf> ret = dao.queryAll();
		CommonTools.closeSession(session);
		return ret;
	}
	public static  ServerConf getServerConfig(String serverConf){
		List<ServerConf>  servers=Server.getAllServerConf();
		for(ServerConf conf:servers){
			if(serverConf.equals(conf.getName())){
				return conf	;
			}
		}
		return null;
	}
}
