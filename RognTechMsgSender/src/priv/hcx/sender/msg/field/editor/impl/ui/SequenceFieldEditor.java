package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Container;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class SequenceFieldEditor extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_38;
	private JTextField textField_39;
	private JTextField textField_40;
	private JTextField textField_41;
	public SequenceFieldEditor() {
		setLayout(new GridLayout(9, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("前  缀：");
		panel_1.add(label);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel label_10 = new JLabel("连接符：");
		panel_1.add(label_10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		panel_1.add(textField_8);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("第一级：");
		panel_2.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_2.add(comboBox);
		
		JLabel label_1 = new JLabel("长度：");
		panel_2.add(label_1);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_37 = new JLabel("起：");
		panel_2.add(label_37);
		
		textField_35 = new JTextField();
		textField_35.setColumns(10);
		panel_2.add(textField_35);
		
		JLabel label_30 = new JLabel("步长：");
		panel_2.add(label_30);
		
		textField_28 = new JTextField();
		textField_28.setColumns(10);
		panel_2.add(textField_28);
		
		JLabel label_11 = new JLabel("格式：");
		panel_2.add(label_11);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		panel_2.add(textField_9);
		
		JLabel label_7 = new JLabel("连接符：");
		panel_2.add(label_7);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_2.add(textField_5);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel label_2 = new JLabel("第二级：");
		panel_3.add(label_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_3.add(comboBox_3);
		
		JLabel label_3 = new JLabel("长度：");
		panel_3.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JLabel label_38 = new JLabel("起：");
		panel_3.add(label_38);
		
		textField_36 = new JTextField();
		textField_36.setColumns(10);
		panel_3.add(textField_36);
		
		JLabel label_31 = new JLabel("步长：");
		panel_3.add(label_31);
		
		textField_29 = new JTextField();
		textField_29.setColumns(10);
		panel_3.add(textField_29);
		
		JLabel label_12 = new JLabel("格式：");
		panel_3.add(label_12);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		panel_3.add(textField_10);
		
		JLabel label_8 = new JLabel("连接符：");
		panel_3.add(label_8);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel_3.add(textField_6);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JLabel label_4 = new JLabel("第三级：");
		panel_4.add(label_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_4.add(comboBox_2);
		
		JLabel label_5 = new JLabel("长度：");
		panel_4.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_4.add(textField_3);
		
		JLabel label_39 = new JLabel("起：");
		panel_4.add(label_39);
		
		textField_37 = new JTextField();
		textField_37.setColumns(10);
		panel_4.add(textField_37);
		
		JLabel label_32 = new JLabel("步长：");
		panel_4.add(label_32);
		
		textField_30 = new JTextField();
		textField_30.setColumns(10);
		panel_4.add(textField_30);
		
		JLabel label_13 = new JLabel("格式：");
		panel_4.add(label_13);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		panel_4.add(textField_11);
		
		JLabel label_9 = new JLabel("连接符：");
		panel_4.add(label_9);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		panel_4.add(textField_7);
		
		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label_6 = new JLabel("第三级：");
		panel.add(label_6);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel.add(comboBox_1);
		
		JLabel label_14 = new JLabel("长度：");
		panel.add(label_14);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		panel.add(textField_15);
		
		JLabel label_40 = new JLabel("起：");
		panel.add(label_40);
		
		textField_38 = new JTextField();
		textField_38.setColumns(10);
		panel.add(textField_38);
		
		JLabel label_33 = new JLabel("步长：");
		panel.add(label_33);
		
		textField_31 = new JTextField();
		textField_31.setColumns(10);
		panel.add(textField_31);
		
		JLabel label_15 = new JLabel("格式：");
		panel.add(label_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		panel.add(textField_16);
		
		JLabel label_16 = new JLabel("连接符：");
		panel.add(label_16);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		panel.add(textField_17);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		JLabel label_17 = new JLabel("第三级：");
		panel_5.add(label_17);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_5.add(comboBox_4);
		
		JLabel label_18 = new JLabel("长度：");
		panel_5.add(label_18);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		panel_5.add(textField_18);
		
		JLabel label_41 = new JLabel("起：");
		panel_5.add(label_41);
		
		textField_39 = new JTextField();
		textField_39.setColumns(10);
		panel_5.add(textField_39);
		
		JLabel label_34 = new JLabel("步长：");
		panel_5.add(label_34);
		
		textField_32 = new JTextField();
		textField_32.setColumns(10);
		panel_5.add(textField_32);
		
		JLabel label_19 = new JLabel("格式：");
		panel_5.add(label_19);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		panel_5.add(textField_19);
		
		JLabel label_20 = new JLabel("连接符：");
		panel_5.add(label_20);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		panel_5.add(textField_20);
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		JLabel label_25 = new JLabel("第三级：");
		panel_6.add(label_25);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_6.add(comboBox_6);
		
		JLabel label_26 = new JLabel("长度：");
		panel_6.add(label_26);
		
		textField_24 = new JTextField();
		textField_24.setColumns(10);
		panel_6.add(textField_24);
		
		JLabel label_42 = new JLabel("起：");
		panel_6.add(label_42);
		
		textField_40 = new JTextField();
		textField_40.setColumns(10);
		panel_6.add(textField_40);
		
		JLabel label_35 = new JLabel("步长：");
		panel_6.add(label_35);
		
		textField_33 = new JTextField();
		textField_33.setColumns(10);
		panel_6.add(textField_33);
		
		JLabel label_27 = new JLabel("格式：");
		panel_6.add(label_27);
		
		textField_25 = new JTextField();
		textField_25.setColumns(10);
		panel_6.add(textField_25);
		
		JLabel label_28 = new JLabel("连接符：");
		panel_6.add(label_28);
		
		textField_26 = new JTextField();
		textField_26.setColumns(10);
		panel_6.add(textField_26);
		
		JPanel panel_7 = new JPanel();
		add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JLabel label_21 = new JLabel("第三级：");
		panel_7.add(label_21);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"无", "数字", "字母（小写）", "字母（大写）", "符号"}));
		panel_7.add(comboBox_5);
		
		JLabel label_22 = new JLabel("长度：");
		panel_7.add(label_22);
		
		textField_21 = new JTextField();
		textField_21.setColumns(10);
		panel_7.add(textField_21);
		
		JLabel label_43 = new JLabel("起：");
		panel_7.add(label_43);
		
		textField_41 = new JTextField();
		textField_41.setColumns(10);
		panel_7.add(textField_41);
		
		JLabel label_36 = new JLabel("步长：");
		panel_7.add(label_36);
		
		textField_34 = new JTextField();
		textField_34.setColumns(10);
		panel_7.add(textField_34);
		
		JLabel label_23 = new JLabel("格式：");
		panel_7.add(label_23);
		
		textField_22 = new JTextField();
		textField_22.setColumns(10);
		panel_7.add(textField_22);
		
		JLabel label_24 = new JLabel("连接符：");
		panel_7.add(label_24);
		
		textField_23 = new JTextField();
		textField_23.setColumns(10);
		panel_7.add(textField_23);
		
		JPanel panel_8 = new JPanel();
		add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JLabel label_29 = new JLabel("后  缀：");
		panel_8.add(label_29);
		
		textField_27 = new JTextField();
		panel_8.add(textField_27);
		textField_27.setColumns(10);
		Border border=new LineBorder(SystemColor.inactiveCaption);
		panel.setBorder(border);
		panel_1.setBorder(border);
		panel_2.setBorder(border);
		panel_3.setBorder(border);
		panel_4.setBorder(border);
		panel_5.setBorder(border);
		panel_6.setBorder(border);
		panel_7.setBorder(border);
		panel_8.setBorder(border);

	}

}
