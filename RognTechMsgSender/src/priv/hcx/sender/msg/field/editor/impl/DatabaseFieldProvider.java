package priv.hcx.sender.msg.field.editor.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.db.dao.DBConfDao;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.ConstFieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ui.DataBaseFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class DatabaseFieldProvider implements FieldEditor {
	private String name = "DatabaseFieldProvider";

	@Override
	public String getEditorName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getFieldConfName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * private DataBaseConfigBean getConfigBean(String fieldId) {
	 * RandomConfigBean bean = null; try { List<RandomConfigBean> confs =
	 * CommonTools.doDBQueryOperation(RandomConfigDao.class, "queryByFieldId",
	 * RandomConfigBean.class, new Class[] { String.class }, fieldId);
	 * 
	 * if (confs.size() > 0) { bean = confs.get(0); } else { bean = new
	 * RandomConfigBean(); bean.setFieldID(fieldId); } } catch (Exception e) {
	 * 
	 * } return bean; }
	 */
	private static DataBaseFieldEditor inst = null;

	private List<DataBaseConfigBean> getConfigBean(String fieldId) {
		List<DataBaseConfigBean> confs = null;
		;
		try {
			confs = CommonTools.doDBQueryOperation(DataBaseConfigDao.class, "queryByFieldId", DataBaseConfigBean.class, new Class[] { String.class }, fieldId);

			if (confs == null || confs.size() <= 0) {
				DataBaseConfigBean bean = new DataBaseConfigBean();
				bean.setFieldID(fieldId);
				confs = new ArrayList<DataBaseConfigBean>();
				confs.add(bean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confs;

	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId, List<MsgField> fields) {

		if (inst == null) {
			inst = new DataBaseFieldEditor();
		}
		try {
			inst.setFields(fields);
			List<DataBaseConfigBean> confs = new ArrayList<DataBaseConfigBean>();
			confs = CommonTools.doDBQueryOperation(DataBaseConfigDao.class, "queryGroupByFieldId", DataBaseConfigBean.class, new Class[] { String.class }, fieldId);

			inst.setConfig(confs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inst;
	}

	@Override
	public void configMsgField(MsgField msg) {
		throw new NotImplementedException("This method is not supported");
	}

	@Override
	public void configcombiedFields(MsgField msg, List<MsgField> fields) {
		List<DataBaseConfigBean> dbConfigs = getConfigBean(msg.getId());
		if(!cachedData.containsKey(dbConfigs.get(0).getDbconnection())){
			loadDbResult(dbConfigs.get(0).getDbconnection(),dbConfigs.get(0).getPreviewSql());
		}
		JSONArray resultData=cachedData.get(dbConfigs.get(0).getDbconnection());
		JSONObject cur=resultData.getJSONObject(0);
		resultData.remove(0);
		
		Map<String, MsgField> confmsgs=new HashMap<String,MsgField>();
		for(DataBaseConfigBean bean:dbConfigs){
			for(MsgField field:fields){
				if(field.getId().equals(bean.getFieldID())){
					confmsgs.put(bean.getFieldMapping().toLowerCase(), field);
					break;
				}
			}
		}
		
		for(String s:cur.keySet()){
			MsgField field=confmsgs.get(s.toLowerCase());
			field.setValue(cur.get(s));
		}
		
	}
	
	Map<String ,JSONArray> cachedData=new HashMap<String,JSONArray>();
	private  void loadDbResult(String  connName,String sql){
		if(!cachedData.containsKey(connName)){
			return ;
		}
		DBConf conf = new DBConf();
		conf.setName(connName);
		List<DBConf> dbconf = null;
		try {
			dbconf = CommonTools.doDBQueryOperation(DBConfDao.class, "queryByName", DBConf.class, new Class[] { DBConf.class }, conf);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		java.sql.Connection conn = null;
		try {
			conn = CommonTools.getDBConfigForTest(dbconf.get(0));
			Statement stm=conn.createStatement();
			ResultSet rst=stm.executeQuery(sql);
			ResultSetMetaData md=rst.getMetaData();
			String[] names=new String[md.getColumnCount()];
			for(int i =1;i<=names.length;++i){
				names[i]=md.getColumnLabel(i);
			}
			JSONArray conRst=new JSONArray();
			while(rst.next()){
				JSONObject row=new JSONObject();
				for(int i=0;i<names.length;++i){
					row.put(names[i], rst.getObject(names[i]));
				}
				conRst.put(row);
			}
			cachedData.put(connName, conRst	);
			
		} catch (Exception e) {
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equals(this.name);
	}

}
