package priv.hcx.sender.server.ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.server.dao.ServerConfDao;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.SenderMainFrame;
import javax.swing.DefaultComboBoxModel;

import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerConfUI extends JDialog {
	private JTextField hostname;
	private JTextField hostport;
	private JTextField configName;
	static ServerConfUI inst;
	static String serverid;

	public static void loadConfig(String config) {
		if (inst == null)
			inst = new ServerConfUI(SenderMainFrame.getMainFrame());

		ServerConf conf = new ServerConf();
		conf.setName(config);
		try {
			List<ServerConf> confs = CommonTools.doQuery(ServerConfDao.class, "queryByName", ServerConf.class, new Class[] { ServerConf.class }, conf);
			if (confs.size() > 0) {
				conf = confs.get(0);
				inst.configName.setText(conf.getName());
				inst.decoder.setSelectedItem(conf.getDecoder());
				inst.encoder.setSelectedItem(conf.getEncoder());
				inst.hostname.setText(conf.getHost());
				inst.hostport.setText(conf.getPort());
				inst.comunitationProtel.setSelectedItem(conf.getProtel());
				inst.serverid = conf.getId();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO 加载配置文件
		GUITool.adjustFrame(inst, false);
		inst.setVisible(true);
	}

	JComboBox comunitationProtel = new JComboBox();
	static private Map<String, ServerConfUI> serverUIs = new HashMap<String, ServerConfUI>();

	public ServerConfUI(JFrame frame) {
		super(frame, true);
		inst.serverid = null;
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				inst.serverid = null;
			}

		});
		setTitle("服务器设置");
		setResizable(false);
		this.setSize(453, 267);
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

		hostname = new JTextField();
		hostname.setBounds(82, 11, 352, 21);
		panel_1.add(hostname);
		hostname.setColumns(10);

		hostport = new JTextField();
		hostport.setColumns(10);
		hostport.setBounds(82, 43, 352, 21);
		panel_1.add(hostport);
		List<MsgEncoder> encoders=CommonTools.loadService(priv.hcx.sender.msg.encoder.MsgEncoder.class);
		String [] names=new String[encoders.size()];
		int i =0;
		for(MsgEncoder encoder:encoders){
			names[i++]=encoder.getEncoderName();
		}
		encoder=new JComboBox(names);
		encoder.setBounds(82, 106, 352, 21);
		
		panel_1.add(encoder);
		List<MsgDecoder> decoders=CommonTools.loadService(MsgDecoder.class);
		names=new String[decoders.size()];
		 i =0;
		 
		for(MsgDecoder dncoder:decoders){
			names[i++]=dncoder.getDecoderName();
		}
		decoder= new JComboBox(names);
		decoder.setBounds(82, 137, 352, 21);
		panel_1.add(decoder);

		JLabel label = new JLabel("配置文件名称：");
		label.setBounds(10, 170, 95, 15);
		panel_1.add(label);

		configName = new JTextField();
		configName.setColumns(10);
		configName.setBounds(100, 167, 334, 21);
		panel_1.add(configName);

		comunitationProtel.setModel(new DefaultComboBoxModel(new String[] { "TCP/IP", "UDP", "SOAP", "HTTP", "HTTPS", "FTP", "SSH", "SFTP", "WebService" }));
		comunitationProtel.setBounds(82, 75, 352, 21);
		panel_1.add(comunitationProtel);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ServerConf con = new ServerConf();
				if (inst.serverid != null) {
					con.setId(inst.serverid);
				}
				con.setDecoder(decoder.getSelectedItem().toString());
				con.setEncoder(encoder.getSelectedItem().toString());
				con.setHost(hostname.getText());
				con.setPort(hostport.getText());
				con.setName(configName.getText());
				con.setProtel(comunitationProtel.getSelectedItem().toString());
				SqlSession session = CommonTools.getSQLSession(true);
				ServerConfDao dao = CommonTools.getMapper(session, ServerConfDao.class);
				if (inst.serverid != null) {
					dao.update(con);
				} else {
					dao.save(con);
				}
				CommonTools.closeSession(session);
			}
		});
		panel_2.add(btnNewButton);
	}

	JComboBox encoder;
	JComboBox decoder ;
}
