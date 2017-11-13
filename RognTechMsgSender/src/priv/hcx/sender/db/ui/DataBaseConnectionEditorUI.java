package priv.hcx.sender.db.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class DataBaseConnectionEditorUI extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public DataBaseConnectionEditorUI() {
		setTitle("数据库连接设置");
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("数据库配置文件名：");
		label.setBounds(10, 15, 117, 15);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(119, 10, 315, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("数 据 库 驱 动 ：");
		label_1.setBounds(10, 45, 117, 15);
		getContentPane().add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(119, 41, 315, 21);
		getContentPane().add(comboBox);
		
		JLabel label_2 = new JLabel("数 据 库 U R L ：");
		label_2.setBounds(10, 75, 117, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("数 据 库 用 户 ：");
		label_3.setBounds(10, 105, 117, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("数 据 库 密 码 ：");
		label_4.setBounds(10, 135, 117, 15);
		getContentPane().add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 72, 315, 21);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 103, 315, 21);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 134, 315, 21);
		getContentPane().add(textField_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 196, 424, 39);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("测试连接");
		button.setBounds(272, 7, 81, 23);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button);
		
		JButton button_1 = new JButton("保存");
		button_1.setBounds(357, 7, 57, 23);
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(button_1);
		
		JLabel label_5 = new JLabel("测 试 语 句 ：");
		label_5.setBounds(10, 166, 117, 15);
		getContentPane().add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(119, 165, 315, 21);
		getContentPane().add(textField_4);
	}
}
