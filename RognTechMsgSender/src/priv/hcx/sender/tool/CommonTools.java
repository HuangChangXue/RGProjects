package priv.hcx.sender.tool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import priv.hcx.sender.db.DBConf;

public class CommonTools {
	public static SqlSession getSQLSession(Boolean autoCommit) {

		return sqlSessionFactory.openSession(autoCommit);
	}

	public static <T> List<T> doDBQueryOperation(Class daoface, String method, Class<T> t, Class<?>[] argtyps, Object... args) throws Exception {
		SqlSession session = getSQLSession(true);
		Object o = getMapper(session, daoface);
		Method m = daoface.getDeclaredMethod(method, argtyps);
		List<T> ret = (List<T>) m.invoke(o, args);
		closeSession(session);
		return ret;
	}

	public static <T> T doDBQueryOperationSingle(Class daoface, String method, Class<T> t, Class<?>[] argtyps, Object... args) throws Exception {
		SqlSession session = getSQLSession(true);
		Object o = getMapper(session, daoface);
		Method m = daoface.getDeclaredMethod(method, argtyps);
		T ret = (T) m.invoke(o, args);
		closeSession(session);
		return ret;
	}

	public static void doDBSaveOrUpdateOperation(Class daoface, String method, Class<?>[] argtyps, Object... args) throws Exception {
		SqlSession session = getSQLSession(true);
		Object o = getMapper(session, daoface);
		Method m = daoface.getDeclaredMethod(method, argtyps);
		m.invoke(o, args);
		closeSession(session);
	}

	public static <T> T getMapper(SqlSession session, Class<T> t) {
		return session.getMapper(t);
	}

	public static void closeSession(SqlSession session) {
		session.close();
	}

	private static SqlSessionFactory sqlSessionFactory;
	static {

		String resource = "priv/hcx/sender/init/res/mybatis.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
		}

	}

	public static <T> ArrayList<T> loadService(Class<T> t) {
		ServiceLoader<T> loader = ServiceLoader.load(t);
		ArrayList<T> res = new ArrayList<T>();
		Iterator<T> it = loader.iterator();
		while (it.hasNext()) {
			res.add(it.next());
		}
		return res;
	}

	public static String createRandomID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	private static char[] charactor = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./;'.+-*\\|<>?:\"{}[]`~!@#$%^&*()_+-=".toCharArray();

	public static String createRandomString(int len) {
		StringBuilder sb = new StringBuilder();
		while (--len > 0) {
			sb.append(charactor[(int) (Math.random() * charactor.length)]);
		}
		return sb.toString();
	}
	
	public static  Connection getDBConfigForTest(DBConf dbconf){
		String clazz=dbconf.getDriverclass();
		String user=dbconf.getUser();
		String pass=dbconf.getPass();
		String url=dbconf.getUrl();
		try {
			Class.forName(clazz);
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
