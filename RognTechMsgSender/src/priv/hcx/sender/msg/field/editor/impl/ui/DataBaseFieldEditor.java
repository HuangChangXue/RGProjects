package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.db.dao.DBConfDao;
import priv.hcx.sender.tool.CommonTools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class DataBaseFieldEditor extends JPanel {
	private JTextField txt_querySql;
	private JTable tbl_fieldmapping;
	private JTable tbl_datapreview;
	JComboBox<String> combo_connection = new JComboBox<String>();
	public DataBaseFieldEditor() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(200, 30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("选择数据库：");
		panel.add(label);
		try {
			List<DBConf> daos=CommonTools.doDBQueryOperation(DBConfDao.class, "queryAll", DBConf.class, new Class[]{}, null);
			if(daos.size()>0){
				for(DBConf conf:daos){
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

		JButton btn_preview = new JButton("预览");
		btn_preview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btn_preview);
		
		JButton btn_save = new JButton("保存");
		panel_1.add(btn_save);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tbl_fieldmapping = new JTable();
		tbl_fieldmapping.setBorder(null);
		tbl_fieldmapping.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u5B57\u6BB5\u540D", "\u6570\u636E\u5E93\u5B57\u6BB5" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbl_fieldmapping);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);

		tbl_datapreview = new JTable();
		scrollPane_1.setViewportView(tbl_datapreview);
	}

}
