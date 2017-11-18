package priv.hcx.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import priv.hcx.sender.init.res.SQLExec;
import priv.hcx.sender.tool.CommonTools;

public class AppInit {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void init() throws Exception {
		System.out.println(CommonTools.createRandomID());
		String resource = "priv/hcx/sender/init/res/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession(true);
		/*
		 * Statement stm=session.getConnection().createStatement(); for(String
		 * s:getInitSql()){ stm.execute(s); }
		 */
		SQLExec exec = session.getMapper(SQLExec.class);
		for (String s : getInitSql()) {
			exec.exec(s);
		}
		session.close();
	}

	public static List<String> getInitSql() throws IOException {
		String resource = "priv/hcx/sender/init/res/init.sql";
		BufferedReader br = new BufferedReader(new InputStreamReader(Resources.getResourceAsStream(resource)));
		String line = null;
		String tmpline = "";
		List<String> sqls = new ArrayList<String>();

		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.length() > 0) {
				tmpline += " " + line;
				if (tmpline.endsWith(";")) {
					tmpline = tmpline.substring(0, tmpline.length() - 1);
					sqls.add(tmpline);
					tmpline = "";
				}
			}
		}
		br.close();
		return sqls;
	}
}
