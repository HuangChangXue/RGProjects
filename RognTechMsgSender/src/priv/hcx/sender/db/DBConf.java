package priv.hcx.sender.db;

import priv.hcx.sender.tool.CommonTools;

public class DBConf {
	String id=CommonTools.createRandomID(),name, url, user, pass, driverclass, testsql;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDriverclass() {
		return driverclass;
	}

	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
	}

	public String getTestsql() {
		return testsql;
	}

	public void setTestsql(String testsql) {
		this.testsql = testsql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
