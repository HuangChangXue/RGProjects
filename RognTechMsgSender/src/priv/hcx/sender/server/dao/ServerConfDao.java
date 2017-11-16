package priv.hcx.sender.server.dao;

import java.util.List;

import priv.hcx.sender.server.ServerConf;

public interface ServerConfDao {
	 void save(ServerConf server);
	 void update(ServerConf server);
	 void delete(ServerConf server);
	List<ServerConf> queryById(ServerConf server);
	List<ServerConf>  queryAll();
	List<ServerConf> queryByName(ServerConf server);
}
