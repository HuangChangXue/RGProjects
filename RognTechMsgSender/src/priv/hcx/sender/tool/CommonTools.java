package priv.hcx.sender.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CommonTools {
	public static SqlSession  getSQLSession(Boolean autoCommit){
		
		return sqlSessionFactory.openSession(autoCommit);
	}
	public static <T>T getMapper(SqlSession session,Class<T> t){
		return session.getMapper(t);
	}
	public static void closeSession(SqlSession session){
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
	private static char[] charactor="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./;'.+-*\\|<>?:\"{}[]`~!@#$%^&*()_+-=".toCharArray();
	
	
	public static String createRandomString(int len){
		StringBuilder sb=new StringBuilder();
		while(--len>0){
			sb.append(charactor[(int)(Math.random()*charactor.length)]);
		}
		return sb.toString();
	}
}
