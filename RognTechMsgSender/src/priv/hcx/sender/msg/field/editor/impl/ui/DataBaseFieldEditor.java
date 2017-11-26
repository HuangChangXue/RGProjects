package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.BoxLayout;

import org.apache.derby.client.am.ResultSet;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.res.MsgFieldDao;
import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.db.dao.DBConfDao;
import priv.hcx.sender.msg.field.editor.impl.DatabaseFieldProvider;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigDao;
import priv.hcx.sender.tool.CommonTools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class DataBaseFieldEditor extends JPanel {
	private JTextField txt_querySql;
	private JTable tbl_fieldmapping;
	private JTable tbl_datapreview;
	JComboBox<String> combo_connection = new JComboBox<String>();
	JComboBox<String> fieldSelections = new JComboBox<String>();
	private List<MsgField> fields = null;

	public DataBaseFieldEditor() {
		setLayout(new BorderLayout(0, 0));
		this.beans = null;
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(200, 30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("选择数据库：");
		panel.add(label);
		try {
			List<DBConf> daos = CommonTools.doDBQueryOperation(DBConfDao.class, "queryAll", DBConf.class, new Class[] {}, null);
			if (daos.size() > 0) {
				for (DBConf conf : daos) {
					combo_connection.addItem(conf.getName());
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel.add(combo_connection);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel lblsql = new JLabel("查询SQL：");
		panel_1.add(lblsql);

		txt_querySql = new JTextField();
		panel_1.add(txt_querySql);
		txt_querySql.setColumns(10);
		BtnActionListener list = new BtnActionListener();
		JButton btn_preview = new JButton("预览");
		btn_preview.setActionCommand("preview");
		btn_preview.addActionListener(list);
		panel_1.add(btn_preview);

		JButton btn_save = new JButton("保存");
		btn_save.setActionCommand("save");
		btn_save.addActionListener(list);
		panel_1.add(btn_save);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tbl_fieldmapping = new JTable();

		tbl_fieldmapping.setBorder(null);
		// tbl_fieldmapping.setCellEditor();

		tbl_fieldmapping.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u5B57\u6BB5\u540D", "\u6570\u636E\u5E93\u5B57\u6BB5" }) {

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@Override
			public Object getValueAt(int row, int column) {
				String key = row + "_" + column;
				if (!jso.keySet().contains(key)) {

					return "";
				}
				return jso.get(key);
			}

			JSONObject jso = new JSONObject();

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				String key = row + "_" + column;
				jso.put(key, aValue);
			}

		});
		tbl_fieldmapping.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(fieldSelections));
		scrollPane.setViewportView(tbl_fieldmapping);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);

		tbl_datapreview = new JTable();
		scrollPane_1.setViewportView(tbl_datapreview);
	}

	private List<DataBaseConfigBean> beans = null;

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
			e.printStackTrace();
		}

		return confs;

	}

	public void setConfig(List<DataBaseConfigBean> configBean) {
		this.beans = configBean;
		DefaultTableModel model = (DefaultTableModel) tbl_fieldmapping.getModel();
		model.setRowCount(fields.size());
		JSONObject mapping = new JSONObject();
		combo_connection.setSelectedIndex(0);
		txt_querySql.setText("");
		for (MsgField field : fields) {

			for (DataBaseConfigBean bean : configBean) {
				if (bean.getDbconnection() != null && bean.getDbconnection().trim().length() > 0) {
					combo_connection.setSelectedItem(bean.getDbconnection());
				}
				if (field.getId().equals(bean.getFieldID())) {
					mapping.put(field.getName(), bean.getFieldMapping());
				}
				txt_querySql.setText(bean.getPreviewSql());
			}
		}

		fieldSelections.removeAllItems();
		fieldSelections.addItem("");
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < fields.size(); i++) {
			String name = fields.get(i).getName();
			model.setValueAt(name, i, 0);
			if (mapping.keySet().contains(name)) {
				model.setValueAt(mapping.getString(name), i, 1);

				items.add(mapping.getString(name));
			} else {
				model.setValueAt("", i, 1);
			}
		}
		for (String item : items) {
			fieldSelections.addItem(item);
		}

		tbl_datapreview.setModel(new DefaultTableModel());
	}

	public List<MsgField> getFields() {
		return fields;
	}

	public void setFields(List<MsgField> fields) {
		this.fields = fields;
	}

	class BtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if ("save".equals(cmd)) {
				DefaultTableModel model = (DefaultTableModel) tbl_fieldmapping.getModel();
				String connecton = combo_connection.getSelectedItem().toString();
				String sql = txt_querySql.getText();

				int cnt = model.getRowCount()-1;
				String groupId = CommonTools.createRandomID();
				while (cnt >= 0) {
					String name = (String) model.getValueAt(cnt, 0);
					String value = (String) model.getValueAt(cnt, 1);
					if (value != null && value.trim().length() > 0) {

						for (MsgField field : fields) {
							if (field.getName().equals(name)&&model.getValueAt(cnt, 0)!=null&&model.getValueAt(cnt, 0).toString().trim().length()>0) {// 找到对应的field
								field.setSrc(new DatabaseFieldProvider().getEditorName());
								try {
									CommonTools.doDBSaveOrUpdateOperation(MsgFieldDao.class, "update", new Class[] { MsgField.class }, field);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								try {
									List<DataBaseConfigBean> configs = CommonTools.doDBQueryOperation(DataBaseConfigDao.class, "queryByFieldId", DataBaseConfigBean.class, new Class[] { String.class },field.getId());
									if(configs!=null&&configs.size()>0){
										DataBaseConfigBean config=configs.get(0);
										config.setGroupId(groupId);
										config.setPreviewSql(sql);
										config.setFieldID(field.getId());
										config.setFieldMapping(value);
										config.setDbconnection(connecton);
										config.setGroupId(groupId);
										CommonTools.doDBSaveOrUpdateOperation(DataBaseConfigDao.class, "update", new Class[] { DataBaseConfigBean.class }, config);
									}
									else {
										DataBaseConfigBean config=new DataBaseConfigBean();
										config.setPreviewSql(sql);
										config.setFieldID(field.getId());
										config.setFieldMapping(value);
										config.setDbconnection(connecton);
										config.setGroupId(groupId);
										CommonTools.doDBSaveOrUpdateOperation(DataBaseConfigDao.class, "save", new Class[] { DataBaseConfigBean.class }, config);
									}
								} catch (Exception e1) {}
							}
						}

					}
					cnt--;
				}
/*
					for (MsgField field : fields) {
						if (field.getName().equals(name)) {// 找到对应的field
							boolean found = false;
							for (DataBaseConfigBean dbbean : bean) {
								if (field.getId().equals(dbbean.getFieldID())) {
									dbbean.setFieldMapping(value);
									try {
										DataBaseConfigBean config = CommonTools.doDBQueryOperationSingle(DataBaseConfigDao.class, "queryById", DataBaseConfigBean.class, new Class[] { String.class },
												dbbean.getId());
										if (config != null) {
											found = true;
											config.setGroupId(groupId);
											config.setPreviewSql(sql);
											config.setFieldID(field.getId());
											config.setFieldMapping(value);
											config.setDbconnection(connecton);
											CommonTools.doDBSaveOrUpdateOperation(DataBaseConfigDao.class, "update", new Class[] { DataBaseConfigBean.class }, dbbean);
											break;
										}
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							}
							if (!found) {
								DataBaseConfigBean config = new DataBaseConfigBean();
								try {
									config.setPreviewSql(sql);
									config.setFieldID(field.getId());
									config.setFieldMapping(value);
									config.setDbconnection(connecton);
									config.setGroupId(groupId == null ? config.getGroupId() : groupId);
									CommonTools.doDBSaveOrUpdateOperation(DataBaseConfigDao.class, "save", new Class[] { DataBaseConfigBean.class }, config);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					if (value != null && value.trim().length() > 0) {
						for (MsgField field : fields) {
							if (field.getName().equals(name)) {
								field.setSrc(new DatabaseFieldProvider().getEditorName());
								try {
									CommonTools.doDBSaveOrUpdateOperation(MsgFieldDao.class, "update", new Class[] { MsgField.class }, field);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								break;
							}
						}
					}
					mapping.put(name, value);
				}
*/
			} else if ("preview".equals(cmd)) {
				String conName = combo_connection.getSelectedItem().toString();
				System.out.println(conName);
				DBConf cond = new DBConf();
				cond.setName(conName);

				try {
					List<DBConf> dbconf = CommonTools.doDBQueryOperation(DBConfDao.class, "queryByName", DBConf.class, new Class[] { DBConf.class }, cond);
					java.sql.Connection conn = null;
					try {
						conn = CommonTools.getDBConfigForTest(dbconf.get(0));
						String querySql = txt_querySql.getText();
						Statement stm = conn.createStatement();
						stm.setFetchSize(10);
						java.sql.ResultSet rs = stm.executeQuery(querySql);
						ResultSetMetaData md = rs.getMetaData();
						int colcnt = md.getColumnCount();
						fieldSelections.removeAllItems();
						fieldSelections.addItem("");
						final List<String> header = new ArrayList<String>();
						for (int i = 1; i <= colcnt; i++) {
							String name = md.getColumnLabel(i);
							header.add(name);
							fieldSelections.addItem(name);
						}
						final List<Object[]> result = new ArrayList<Object[]>();
						while (rs.next()) {
							Object[] row = new Object[header.size()];
							for (int i = 1; i <= colcnt; ++i) {
								row[i - 1] = rs.getObject(i);
							}
							result.add(row);
						}

						DefaultTableModel tbm = new DefaultTableModel() {

							@Override
							public int getRowCount() {
								return result.size();
							}

							@Override
							public int getColumnCount() {
								return header.size();
							}

							@Override
							public String getColumnName(int column) {
								return header.get(column);
							}

							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}

							@Override
							public Object getValueAt(int row, int column) {
								return result.get(row)[column];
							}

						};

						tbl_datapreview.setModel(tbm);
						tbl_datapreview.updateUI();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {

						try {
							conn.close();
						} catch (Exception es) {
						}
					}
				} catch (Exception e1) {

				}

			}
		}

	}
}
