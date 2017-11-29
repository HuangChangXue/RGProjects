package priv.hcx.sender.msg.field.editor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigDao;
import priv.hcx.sender.msg.field.editor.impl.ui.FileFieldEditor;
import priv.hcx.sender.tool.CommonTools;

public class FileFieldProvider implements FieldEditor {
	private String name = "FileFieldProvider";

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
	 * private FileConfigBean getConfigBean(String fieldId) { RandomConfigBean
	 * bean = null; try { List<RandomConfigBean> confs =
	 * CommonTools.doDBQueryOperation(RandomConfigDao.class, "queryByFieldId",
	 * RandomConfigBean.class, new Class[] { String.class }, fieldId);
	 * 
	 * if (confs.size() > 0) { bean = confs.get(0); } else { bean = new
	 * RandomConfigBean(); bean.setFieldID(fieldId); } } catch (Exception e) {
	 * 
	 * } return bean; }
	 */
	private static FileFieldEditor inst = null;

	private List<FileConfigBean> getConfigBean(String fieldId) {
		List<FileConfigBean> confs = null;
		;
		try {
			confs = CommonTools.doDBQueryOperation(FileConfigDao.class, "queryGroupByFieldId", FileConfigBean.class, new Class[] { String.class }, fieldId);

			if (confs == null || confs.size() <= 0) {
				FileConfigBean bean = new FileConfigBean();
				bean.setFieldID(fieldId);
				confs = new ArrayList<FileConfigBean>();
				confs.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return confs;

	}

	@Override
	public JPanel getEditPaneByFieldId(String fieldId, List<MsgField> fields) {

		if (inst == null) {
			inst = new FileFieldEditor();
		}
		try {
			inst.setFields(fields);
			List<FileConfigBean> confs = new ArrayList<FileConfigBean>();
			confs = CommonTools.doDBQueryOperation(FileConfigDao.class, "queryGroupByFieldId", FileConfigBean.class, new Class[] { String.class }, fieldId);
			if (confs.size() <= 0) {
				FileConfigBean bean = new FileConfigBean();
				bean.setFieldID(fieldId);
				confs.add(bean);
			}
			inst.setConfig(confs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inst;
	}

	@Override
	public void configMsgField(MsgField msg) {
		throw new NotImplementedException("nooooooo");
	}

	@Override
	public void configcombiedFields(MsgField msg, List<MsgField> fields) {
		List<FileConfigBean> configs = getConfigBean(msg.getId());
		String fileName = configs.get(0).getFileName();
		String filesep = configs.get(0).getFieldSep();
		if (!cachedData.containsKey(fileName)) {
			loadFileData(fileName, filesep);
		}
		Map<String, MsgField> confmsgs = new HashMap<String, MsgField>();
		for (FileConfigBean bean : configs) {
			for (MsgField field : fields) {
				if (field.getId().equals(bean.getFieldID())) {
					confmsgs.put(bean.getFieldMapping().toLowerCase(), field);
					break;
				}
			}
		}
		JSONArray data = cachedData.get(fileName);
		JSONObject row = data.getJSONObject(0);
		data.remove(0);
		for (String key : row.keySet()) {
			if (confmsgs.containsKey(key)) {
				confmsgs.get(key).setValue(row.get(key));
			}
		}
	}

	private synchronized void loadFileData(String name, String sep) {
		File file = new File(name);
		BufferedReader br = null;
		String[] header = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			JSONArray fileData = new JSONArray();
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0) {
					if (header == null) {
						header = line.split(sep);
					} else {
						JSONObject row = new JSONObject();
						String[] ro = line.split(sep);
						for (int i = 0; i < header.length; ++i) {
							row.put(header[i], ro[i]);
						}
						fileData.put(row);
					}
				}
			}
			cachedData.put(name, fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

	}

	private static Map<String, JSONArray> cachedData = new HashMap<String, JSONArray>();

	@Override
	public boolean isAviableforName(String name) {
		// TODO Auto-generated method stub
		return name.equals(this.name);
	}

}
