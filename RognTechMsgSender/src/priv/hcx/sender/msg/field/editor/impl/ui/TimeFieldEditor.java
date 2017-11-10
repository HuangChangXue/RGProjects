package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class TimeFieldEditor extends JPanel {
	private final JRadioButton rdbtnNewRadioButton = new JRadioButton("当前时间");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public TimeFieldEditor() {
		setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(rdbtnNewRadioButton);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JRadioButton radioButton = new JRadioButton("指定时间");
		panel_1.add(radioButton);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_1.add(comboBox_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JRadioButton radioButton_1 = new JRadioButton("动态生成");
		panel_2.add(radioButton_1);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_2.add(comboBox_2);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
	}

}
