package priv.hcx.sender.msg.editor.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.res.MsgFieldDao;
import priv.hcx.sender.msg.editor.MsgEditor;
import priv.hcx.sender.msg.header.HeaderEditor;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.ComboboxListCellRender;
import priv.hcx.sender.view.Const;

public class DefaultMsgEditor implements MsgEditor, Const {
	/*
	 * @Override public JPanel getEditorPanel(String transactionName, String
	 * configFileName) { JPanel editorContainerPanel = new JPanel();
	 * editorContainerPanel.setLayout(new BorderLayout()); JPanel headSetting =
	 * new JPanel(); headSetting.setBorder(new TitledBorder("消息头设置")); JPanel
	 * headerPanel = new JPanel(); JLabel jbl = new
	 * JLabel(GUITool.getName(MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE));
	 * JLabel jblfile = new
	 * JLabel(GUITool.getName(MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE_CONFIG
	 * )); headerPanel.add(jbl); headerPanel.add(jblfile);
	 * headSetting.add(headerPanel);
	 * 
	 * editorContainerPanel.add(headSetting, BorderLayout.NORTH); JPanel
	 * fieldTablePanel = new JPanel(); fieldTablePanel.setBorder(new
	 * TitledBorder("字段设置")); fieldTablePanel.add(new JLabel("消息头设置选项"));
	 * editorContainerPanel.add(fieldTablePanel, BorderLayout.CENTER); return
	 * editorContainerPanel; }
	 */
	FieldTableModel tableModle = new FieldTableModel();
	private String transactionid = null;

	@Override
	public JPanel getEditorPanel(String transactionid) {
		if (editors.containsKey(transactionid)) {
			return editors.get(transactionid);
		}
		this.transactionid = transactionid;
		JPanel editorContainerPanel = new JPanel();
		editorContainerPanel.setLayout(new BorderLayout());
		JPanel headSetting = new JPanel();
		headSetting.setBorder(new TitledBorder("消息头设置"));
		JPanel headerPanel = new JPanel();
		JLabel jbl = new JLabel(GUITool.getName(MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE));
		JLabel jblfile = new JLabel(GUITool.getName(MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE_CONFIG));
		headerPanel.add(jbl);
		final JComboBox<String> confile = new JComboBox<String>();
		JComboBox<String> headertype = new JComboBox<String>(getHeaderType());
		headertype.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String[] list = getHeaderTypeConfigFile(e.getItem().toString());
					confile.removeAllItems();
					for (String s : list) {
						if (s != null) {
							confile.addItem(s);
						}
					}
				}
			}

		});
		headertype.setEditable(false);
		headertype.setSelectedIndex(0);
		headerPanel.add(headertype);
		headerPanel.add(jblfile);

		confile.setEditable(true);
		headerPanel.add(confile);
		headerPanel.add(new JButton("编辑/新建"));
		headSetting.add(headerPanel);

		editorContainerPanel.add(headSetting, BorderLayout.NORTH);

		try {
			List<MsgField> msgs = CommonTools.doDBQueryOperation(MsgFieldDao.class, "queryByTransactonId", MsgField.class, new Class[] { String.class }, new String[] { transactionid });
			for (MsgField msg : msgs) {
				tableModle.addRow(msg);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// tableModle.setColumnIdentifiers(new String[] { "名字", "描述", "来源"});
		final JTable table = new JTable(tableModle);
		tableModle.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					FieldTableModel model = (FieldTableModel) e.getSource();
					MsgField field = model.getFieldAt(table.convertRowIndexToModel(table.getSelectedRow()));

					try {
						CommonTools.doDBSaveOrUpdateOperation(MsgFieldDao.class, "update", new Class[] { MsgField.class }, field);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		// table.setBackground(headerPanel.getBackground());
		// table.setGridColor(headerPanel.getBackground());
		// tableModle.addRow(new String[] { "名字", "描述", "来源" });
		// tableModle.addRow(new String[] { "名字", "描述", "来源" });
		// tableModle.addRow(new String[] { "名字", "描述", "来源"});

		new TablePopupMenu(table, this.transactionid);
		table.getSelectionModel().addListSelectionListener(TableRowSelectionListener.getInst(table));
		JComboBox<JButton> sourceCombobox = new JComboBox<JButton>();
		sourceCombobox.addItemListener(FieldSouceChangeListener.getInst(table));
		DefaultCellEditor sourceEditor = new DefaultCellEditor(sourceCombobox) {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue() {
				JButton btn = (JButton) super.getCellEditorValue();
				return btn.getText();
			}

		};
		List<priv.hcx.sender.msg.field.editor.FieldEditor> sourceProviders = CommonTools.loadService(priv.hcx.sender.msg.field.editor.FieldEditor.class);
		for (priv.hcx.sender.msg.field.editor.FieldEditor provider : sourceProviders) {
			JButton button = new JButton(GUITool.getName(provider.getEditorName()));
			button.setActionCommand(provider.getEditorName());
			sourceCombobox.addItem(button);
		}
		sourceCombobox.setRenderer(ComboboxListCellRender.getInst());
		table.getColumnModel().getColumn(2).setCellEditor(sourceEditor);

		table.setFillsViewportHeight(true);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBorder(new TitledBorder("字段值设置"));
		editorContainerPanel.add(tablePane, BorderLayout.CENTER);
		editors.put(transactionid, editorContainerPanel);
		return editorContainerPanel;
	}

	private static Map<String, JPanel> editors = new HashMap<String, JPanel>();

	private String[] getHeaderTypeConfigFile(String name) {
		List<HeaderEditor> headers = CommonTools.loadService(priv.hcx.sender.msg.header.HeaderEditor.class);
		HeaderEditor editor = null;
		for (int i = 0; i < headers.size(); ++i) {
			if (headers.get(i).getEditorName().equals(name)) {
				editor = headers.get(i);
			}
		}
		return getHeaderConfigFileList(editor);
	}

	private String[] getHeaderConfigFileList(HeaderEditor editor) {
		return editor.getConfigList().toArray(new String[] {});
	}

	private String[] getHeaderType() {
		List<HeaderEditor> headers = CommonTools.loadService(priv.hcx.sender.msg.header.HeaderEditor.class);
		String[] rets = new String[headers.size()];
		for (int i = 0; i < rets.length; ++i) {
			rets[i] = headers.get(i).getEditorName();
		}
		return rets;
	}

	@Override
	public boolean saveAllMsgConfig() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveMsgConfig(String transactionName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message getMessageByTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
