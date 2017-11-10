package priv.hcx.sender.msg.field.editor.impl.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTable;

import priv.hcx.sender.tool.CommonTools;

public class ConstFieldEditor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ConstFieldEditor() {
		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(323, 376));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		// panel.setLayout(null);

		JCheckBox checkBox = new JCheckBox("允许为空");
		panel.add(checkBox);
		checkBox.setBounds(36, 5, 73, 23);

		JCheckBox checkBox_1 = new JCheckBox("允许换行");
		checkBox_1.setBounds(114, 5, 73, 23);
		panel.add(checkBox_1);
		 JTextArea textArea = new JTextArea();
		add(textArea, BorderLayout.CENTER);
		JButton button = new JButton("生成随机值");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(button);
		/*
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(CommonTools.createRandomString(10));
			}
		});
		*/
	
		// button.setVerticalAlignment(SwingConstants.BOTTOM);
		button.setBounds(65, 33, 93, 23);
		


//		table = new JTable();
//		JScrollPane jsp = new JScrollPane();
//		jsp.add(table);
//		add(jsp, BorderLayout.SOUTH);
	}
}
