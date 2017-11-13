package priv.hcx.sender.server.ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ServerConfUI extends JFrame {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	public ServerConfUI() {
		setTitle("服务器设置");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("服务器地址：");
		label_1.setBounds(10, 16, 83, 15);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("服务器端口：");
		label_2.setBounds(10, 47, 83, 15);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("通信  协议：");
		label_3.setBounds(10, 78, 83, 15);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("编  码  器：");
		label_4.setBounds(10, 109, 83, 15);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("解  码  器：");
		label_5.setBounds(10, 140, 83, 15);
		panel_1.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(82, 11, 352, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(82, 43, 352, 21);
		panel_1.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(82, 106, 352, 21);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(82, 137, 352, 21);
		panel_1.add(comboBox_1);
		
		JLabel label = new JLabel("配置文件名称：");
		label.setBounds(10, 170, 95, 15);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 167, 334, 21);
		panel_1.add(textField);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(82, 75, 352, 21);
		panel_1.add(comboBox_2);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("保存");
		panel_2.add(btnNewButton);
	}
}
