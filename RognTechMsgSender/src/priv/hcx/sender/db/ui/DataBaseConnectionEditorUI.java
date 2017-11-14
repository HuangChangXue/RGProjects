package priv.hcx.sender.db.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;

import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.view.SenderMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseConnectionEditorUI extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField configName;
	private JTextField url;
	private JTextField user;
	private JTextField password;
	private JTextField testSql;

	static private Map<String ,DataBaseConnectionEditorUI> mapping=new HashMap<String,DataBaseConnectionEditorUI>();
	public static  void loadConfig(String config){
		DataBaseConnectionEditorUI inst=null;
		if(mapping.containsKey(config)){
			inst=mapping.get(config);
		}
		else {
			inst=new DataBaseConnectionEditorUI(SenderMainFrame.getMainFrame(),true);
		}
		inst.setVisible(true);
	}
	public DataBaseConnectionEditorUI(JFrame owner,  boolean modal ) {
		super(owner,"数据库连接设置",true);
		this.setSize(444, 272);
//		setTitle();
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("数据库配置文件名：");
		label.setBounds(10, 15, 117, 15);
		getContentPane().add(label);

		configName = new JTextField();
		configName.setBounds(119, 10, 315, 21);
		getContentPane().add(configName);
		configName.setColumns(10);

		JLabel label_1 = new JLabel("数 据 库 驱 动 ：");
		label_1.setBounds(10, 45, 117, 15);
		getContentPane().add(label_1);
		List<java.sql.Driver> drivers = CommonTools.loadService(java.sql.Driver.class);
		String [] ds=new String[]{};
			ds=	new String[drivers.size()];
		int i=0;
		for (java.sql.Driver driver : drivers) {
			ds[i++]=driver.getClass().getName();
		}
		
		JComboBox driverClass = new JComboBox();
		driverClass.setBounds(119, 41, 315, 21);
		getContentPane().add(driverClass);

		JLabel label_2 = new JLabel("数 据 库 U R L ：");
		label_2.setBounds(10, 75, 117, 15);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("数 据 库 用 户 ：");
		label_3.setBounds(10, 105, 117, 15);
		getContentPane().add(label_3);

		JLabel label_4 = new JLabel("数 据 库 密 码 ：");
		label_4.setBounds(10, 135, 117, 15);
		getContentPane().add(label_4);

		url = new JTextField();
		url.setColumns(10);
		url.setBounds(119, 72, 315, 21);
		getContentPane().add(url);

		user = new JTextField();
		user.setColumns(10);
		user.setBounds(119, 103, 315, 21);
		getContentPane().add(user);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(119, 134, 315, 21);
		getContentPane().add(password);
		testSql = new JTextField();
		testSql.setColumns(10);
		testSql.setBounds(119, 165, 315, 21);
		getContentPane().add(testSql);
		JPanel panel = new JPanel();
		panel.setBounds(10, 196, 424, 39);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton button = new JButton("测试连接");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		button.setBounds(272, 7, 81, 23);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button);

		JButton button_1 = new JButton("保存");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(357, 7, 57, 23);
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(button_1);

		JLabel label_5 = new JLabel("测 试 语 句 ：");
		label_5.setBounds(10, 166, 117, 15);
		getContentPane().add(label_5);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
