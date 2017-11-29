package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.json.JSONObject;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.res.MsgFieldDao;
import priv.hcx.sender.msg.field.editor.impl.FileFieldProvider;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.DataBaseConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.FileConfigDao;
import priv.hcx.sender.tool.CommonTools;

public class FileFieldEditor extends JPanel {
	private final JLabel lblNewLabel = new JLabel("选择文件：");
	private JTextField txt_file;
	private JTable table_preview;
	private JTable tab_fieldMapping;
	private JTextField txt_sep;

	public FileFieldEditor() {
		setLayout(new BorderLayout(0, 0));
		FieldActionListener list = new FieldActionListener();
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(lblNewLabel);

		txt_file = new JTextField();
		txt_file.setEnabled(false);
		txt_file.setEditable(false);
		txt_file.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				if (file != null) {
					txt_file.setText(file.getAbsolutePath());
					txt_file.postActionEvent();
				}
			}
		});
		panel.add(txt_file);
		txt_file.setColumns(10);
		txt_file.setActionCommand("chooseFile");
		txt_file.addActionListener(list);
		JLabel lblNewLabel_1 = new JLabel("分隔符:");
		panel.add(lblNewLabel_1);

		txt_sep = new JTextField();
		txt_sep.addActionListener(list);
		txt_sep.setActionCommand("sepFile");
		txt_sep.setText("\\|");
		panel.add(txt_sep);
		txt_sep.setColumns(10);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		table_preview = new JTable();
		scrollPane.setViewportView(table_preview);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		tab_fieldMapping = new JTable();

		scrollPane_1.setViewportView(tab_fieldMapping);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btn_save = new JButton("保存");
		btn_save.setActionCommand("save");
		btn_save.addActionListener(list);
		panel_1.add(btn_save);
	}

	List<MsgField> fields = null;

	public void setFields(List<MsgField> fields) {
		this.fields = fields;
	}

	private String groupId = null;
	List<FileConfigBean> confs = null;
	JComboBox<String> fieldSelections = new JComboBox<String>();

	public void setConfig(List<FileConfigBean> confs) {
		this.confs = confs;
		final JSONObject mapping = new JSONObject();
		fieldSelections.removeAllItems();
		fieldSelections.addItem("");
		table_preview.setModel(new DefaultTableModel());
		txt_file.setText("");
		txt_sep.setText("\\|");
		for (FileConfigBean conf : confs) {
			groupId = conf.getGroupId();
			txt_sep.setText(conf.getFieldSep());
			txt_file.setText(conf.getFileName());
			for (MsgField field : fields) {
				if (field.getId().equals(conf.getFieldID())) {
					mapping.put(field.getName(), conf.getFieldMapping());
					fieldSelections.addItem(conf.getFieldMapping());
				}
			}
		}

		DefaultTableModel model = new DefaultTableModel() {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public Object getValueAt(int row, int column) {
				String name = fields.get(row).getName();
				if (column == 0) {
					return name;
				} else {
					if (mapping.has(name)) {
						return mapping.get(name);
					}
					return "";
				}
			}

			@Override
			public int getRowCount() {
				return fields.size();
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				String name = fields.get(row).getName();
				if (column == 0) {

				} else {
					mapping.put(name, aValue);

				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}

		};
		tab_fieldMapping.setModel(model);

		tab_fieldMapping.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(fieldSelections));
	}

	class FieldActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if ("save".equals(cmd)) {

				String sep = txt_sep.getText();
				String file = txt_file.getText();
				DefaultTableModel dtm = (DefaultTableModel) tab_fieldMapping.getModel();
				int rowCnt = dtm.getRowCount();
				String ffName = new FileFieldProvider().getEditorName();
				for (int i = 0; i < rowCnt; i++) {// groupId
					String name = dtm.getValueAt(i, 0).toString();

					for (MsgField field : fields) {
						if (dtm.getValueAt(i, 1) != null && dtm.getValueAt(i, 1).toString().trim().length() > 0) {
							if (field.getName().equals(name)) {

								field.setSrc(ffName);
								try {
									CommonTools.doDBSaveOrUpdateOperation(MsgFieldDao.class, "update", new Class[] { MsgField.class }, field);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								boolean isFound = false;
								for (FileConfigBean config : confs) {
									if (config.getFieldID().equals(field.getId())) {
										
										config.setFieldMapping(dtm.getValueAt(i, 1).toString());
										config.setFieldSep(sep);
										config.setFileName(file);
										try {
											if (CommonTools.doDBQueryOperationSingle(FileConfigDao.class, "queryById", FileConfigBean.class, new Class[] { String.class }, config.getId()) != null) {
												isFound = true;
												try {
													CommonTools.doDBSaveOrUpdateOperation(FileConfigDao.class, "update", new Class[] { FileConfigBean.class }, config);
												} catch (Exception e1) {
													e1.printStackTrace();
												}
											}
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									}
								}
								if (!isFound) {

									FileConfigBean config = new FileConfigBean();

									config.setFieldID(field.getId());
									config.setFieldMapping(dtm.getValueAt(i, 1).toString());
									config.setFieldSep(sep);
									config.setFileName(file);
									config.setGroupId(groupId);
									try {
										CommonTools.doDBSaveOrUpdateOperation(FileConfigDao.class, "save", new Class[] { FileConfigBean.class }, config);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}
							}
						} else {
							if (field.getName().equals(name)) {
								for (FileConfigBean config : confs) {
									if (config.getFieldID().equals(field.getId())) {
										try {
											CommonTools.doDBSaveOrUpdateOperation(FileConfigDao.class, "delete", new Class[] { FileConfigBean.class }, config);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									}
								}
							}
						}
					}
				}

			} else if ("chooseFile".equalsIgnoreCase(cmd) || "sepFile".equalsIgnoreCase(cmd)) {
				String file = txt_file.getText();
				final String sep = txt_sep.getText();
				if (file != null && file.trim().length() > 0 && sep != null && sep.length() > 0) {
					File input = new File(file);
					final List<String> lines = readFile(input);
					if (lines.size() > 0) {
						String header = lines.get(0);
						final String[] headers = header.split(sep);

						fieldSelections.removeAllItems();
						fieldSelections.addItem("");
						for (int i = 0; i < headers.length; ++i) {

							fieldSelections.addItem(headers[i]);
						}
						for (int i = 1; i < lines.size(); ++i) {
							String line = lines.get(i);
							if (line != null && line.trim().length() > 0) {

							}
						}

						DefaultTableModel tbm = new DefaultTableModel() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public int getRowCount() {
								return lines.size() - 1;
							}

							@Override
							public int getColumnCount() {

								return headers.length;
							}

							@Override
							public String getColumnName(int column) {
								return headers[column];
							}

							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}

							@Override
							public Object getValueAt(int row, int column) {
								String line = lines.get(row + 1);
								if (line != null && line.trim().length() > 0) {
									try {
										String[] cols = line.split(sep);
										return cols[column];
									} catch (Exception e) {
									}
								}
								return "";
							}

						};

						table_preview.setModel(tbm);

					}

				}
			}
		}

		private List<String> readFile(File input) {
			List<String> lines = new ArrayList<String>();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
				String line = null;
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			return lines;

		}
	}

}
