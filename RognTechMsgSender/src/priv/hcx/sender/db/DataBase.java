package priv.hcx.sender.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import priv.hcx.sender.db.dao.DBConfDao;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.server.dao.ServerConfDao;
import priv.hcx.sender.tool.CommonTools;

public class DataBase {
	public static List<DBConf> getAllDbConf() {

		SqlSession session = CommonTools.getSQLSession(true);
		DBConfDao dao = CommonTools.getMapper(session, DBConfDao.class);
		List<DBConf> ret = dao.queryAll();
		CommonTools.closeSession(session);
		return ret;

	}

	static void openNewConnectionWindow() {

	}

	static void openEditConnectionWindow() {

	}
}
