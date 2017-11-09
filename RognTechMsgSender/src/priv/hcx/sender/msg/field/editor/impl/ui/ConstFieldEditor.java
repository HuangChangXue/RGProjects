package priv.hcx.sender.msg.field.editor.impl.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

public class ConstFieldEditor extends JPanel {
	public ConstFieldEditor() {
		setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("允许为空");
		checkBox.setBounds(20, 262, 73, 23);
		add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("允许换行");
		checkBox_1.setBounds(20, 287, 73, 23);
		add(checkBox_1);
		
		JButton button = new JButton("生成随机值");
		button.setBounds(99, 262, 93, 48);
		add(button);
		
		JTextArea textArea = new JTextArea();
		add(textArea);
		textArea.setBounds(20, 20, 188, 224);
		textArea.setPreferredSize(new Dimension(188,224));
//		add(textArea);
		//		add(textArea);
				textArea.setLineWrap(true);
				textArea.setAutoscrolls(true);
		this.setPreferredSize(new Dimension(223, 329));
	}
}
