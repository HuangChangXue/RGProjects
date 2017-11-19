package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class FileFieldEditor extends JPanel {
	private final JLabel lblNewLabel = new JLabel("选择文件：");
	private JTextField txt_file;
	private JTable table_preview;
	private JTable tab_fieldMapping;
	private JTextField txt_sep;

	public FileFieldEditor() {
		setLayout(new BorderLayout(0, 0));

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
			}
		});
		panel.add(txt_file);
		txt_file.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("分隔符:");
		panel.add(lblNewLabel_1);

		txt_sep = new JTextField();
		txt_sep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		txt_sep.setText("|");
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
		tab_fieldMapping.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u5B57\u6BB5\u540D\u79F0", "\u6587\u4EF6\u57DF\u540D\u79F0" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(tab_fieldMapping);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_save = new JButton("保存");
		panel_1.add(btn_save);
	}

}
