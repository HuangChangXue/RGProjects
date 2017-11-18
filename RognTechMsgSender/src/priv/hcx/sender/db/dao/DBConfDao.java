package priv.hcx.sender.db.dao;

import java.util.List;

import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.server.ServerConf;

public interface DBConfDao {
	void save(DBConf server);

	void update(DBConf server);

	void delete(DBConf server);

	List<DBConf> queryById(DBConf server);

	List<DBConf> queryAll();

	List<DBConf> queryByName(DBConf server);
}
