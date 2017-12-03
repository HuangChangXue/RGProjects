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
import priv.hcx.sender.protel.ProtelHandler;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.server.dao.ServerConfDao;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.tool.ServerTool;
import priv.hcx.sender.view.SenderMainFrame;
import javax.swing.DefaultComboBoxModel;

import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class ServerConfUI extends JDialog {
	private JTextField hostname;
	private JTextField hostport;
	private JTextField configName;
	static ServerConfUI inst;
	static String serverid;
	JButton btn_Save = new JButton("保存");
	JButton btn_start = new JButton("启动监听");
	public static void loadConfig(String config) {
		if (inst == null)
			inst = new ServerConfUI(SenderMainFrame.getMainFrame());

		ServerConf conf = new ServerConf();
		conf.setName(config);
		try {
			List<ServerConf> confs = CommonTools.doDBQueryOperation(ServerConfDao.class, "queryByName", ServerConf.class, new Class[] { ServerConf.class }, conf);
			if (confs.size() > 0) {
				conf = confs.get(0);
				inst.configName.setText(conf.getName());
				inst.decoder.setSelectedItem(conf.getDecoder());
				inst.encoder.setSelectedItem(conf.getEncoder());
				inst.hostname.setText(conf.getHost());
				inst.hostport.setText(conf.getPort());
				inst.comunitationProtel.setSelectedItem(conf.getProtel());
				inst.serverid = conf.getId();
				inst.btn_encoderConfig.setText(conf.getEncoderConfigName());
				inst.btn_decoderConfig.setText(conf.getDecoderConfigName());
				inst.serverType.setSelectedItem(conf.getServerType());
				if("remote".equals(conf.getServerType())){
					inst.btn_start.setVisible(false);
					inst.btn_start.setEnabled(false);
				}
				else {
					inst.btn_start.setVisible(true);
					inst.btn_start.setEnabled(true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO 加载配置文件
		GUITool.adjustFrame(inst, false);
		inst.setVisible(true);
	}

	JComboBox<String> comunitationProtel = new JComboBox<String>();
	JComboBox<String> serverType = new JComboBox<String>(new String[]{"remote","locale"});
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

		JLabel label_11 = new JLabel("服务器类型：");
		label_11.setBounds(10, 13, 83, 15);
		panel_1.add(label_11);
		serverType.setBounds(82, 11, 352, 21);
		serverType.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					if("locale".equals(e.getItem())){
						btn_start.setEnabled(true);
						btn_start.setVisible(true);
					}
					else {
						btn_start.setEnabled(false);
						btn_start.setVisible(false);
					}
				
				}
				
			}
			
		});
		panel_1.add(serverType);
		//

		JLabel label_1 = new JLabel("服务器地址：");
		label_1.setBounds(10, 45, 83, 15);
		panel_1.add(label_1);
		hostname = new JTextField();
		hostname.setBounds(82, 43, 130, 21);
		panel_1.add(hostname);

		hostname.setColumns(10);

		hostport = new JTextField();
		hostport.setColumns(10);
		hostport.setBounds(296, 43, 138, 21);
		JLabel label_2 = new JLabel("服务器端口：");
		label_2.setBounds(220, 45, 83, 15);
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

		panel_1.add(hostport);
		List<MsgEncoder> encoders = CommonTools.loadService(priv.hcx.sender.msg.encoder.MsgEncoder.class);
		String[] names = new String[encoders.size()];
		int i = 0;
		for (MsgEncoder encoder : encoders) {
			names[i++] = encoder.getEncoderName();
		}
		encoder = new JComboBox(names);
		encoder.setBounds(82, 106, 204, 21);

		panel_1.add(encoder);
		List<MsgDecoder> decoders = CommonTools.loadService(MsgDecoder.class);
		names = new String[decoders.size()];
		i = 0;

		for (MsgDecoder dncoder : decoders) {
			names[i++] = dncoder.getDecoderName();
		}
		decoder = new JComboBox<String>(names);
		decoder.setBounds(82, 137, 204, 21);
		panel_1.add(decoder);

		JLabel label = new JLabel("配置文件名称：");
		label.setBounds(10, 170, 95, 15);
		panel_1.add(label);

		configName = new JTextField();
		configName.setColumns(10);
		configName.setBounds(100, 167, 334, 21);
		panel_1.add(configName);
		List<ProtelHandler> handlers = CommonTools.loadService(ProtelHandler.class);
		String[] hnames = new String[handlers.size()];
		int idx = 0;
		for (ProtelHandler handler : handlers) {
			hnames[idx++] = handler.getHandlerName();
		}
		comunitationProtel.setModel(new DefaultComboBoxModel(hnames));
		comunitationProtel.setBounds(82, 75, 352, 21);
		panel_1.add(comunitationProtel);

		BtnActionListener list = new BtnActionListener();
		btn_encoderConfig.setBounds(296, 105, 138, 23);
		panel_1.add(btn_encoderConfig);
		btn_encoderConfig.addActionListener(list);
		btn_encoderConfig.setActionCommand("openEncoder");
		btn_decoderConfig.setBounds(296, 136, 138, 23);
		panel_1.add(btn_decoderConfig);
		btn_decoderConfig.addActionListener(list);
		btn_decoderConfig.setActionCommand("openDecoder");
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		ActionListener listSS=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();
				if("save".equals(cmd)){


					ServerConf con = new ServerConf();
					if (inst.serverid != null) {
						con.setId(inst.serverid);
					}
					con.setDecoder(decoder.getSelectedItem().toString());
					con.setEncoder(encoder.getSelectedItem().toString());
					con.setHost(hostname.getText());
					con.setPort(hostport.getText());
					con.setName(configName.getText());
					con.setEncoderConfigName(btn_encoderConfig.getText());
					con.setDecoderConfigName(btn_decoderConfig.getText());
					con.setProtel(comunitationProtel.getSelectedItem().toString());
					con.setServerType(serverType.getSelectedItem().toString());
					SqlSession session = CommonTools.getSQLSession(true);
					ServerConfDao dao = CommonTools.getMapper(session, ServerConfDao.class);
					if (inst.serverid != null) {
						dao.update(con);
					} else {
						dao.save(con);
					}
					CommonTools.closeSession(session);
				
				}
				else if("start".equals(cmd)){
					btn_start.setActionCommand("stop");
					btn_start.setText("停止监听");
					ServerTool.startServer(hostname.getText(), hostport.getText());
				}
				else if("stop".equals(cmd)){
					btn_start.setActionCommand("start");
					btn_start.setText("启动监听");
					ServerTool.stopServer(hostname.getText(), hostport.getText());
				}
				
			}
		};
		btn_Save.addActionListener(listSS);
		btn_Save.setActionCommand("save");
		btn_start.addActionListener(listSS);
		btn_start.setActionCommand("start");
		panel_2.add(btn_Save);
		panel_2.add(btn_start);
	}

	JButton btn_encoderConfig = new JButton("");
	JButton btn_decoderConfig = new JButton("");
	JComboBox<String> encoder;
	JComboBox<String> decoder;

	class BtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if ("openDecoder".equals(cmd)) {
				String txt = btn_decoderConfig.getText();
				final MsgDecoder decodera = CommonTools.getDecoderByName(decoder.getSelectedItem().toString());
				JDialog ui = decodera.editDecoderConfigDialog(txt);
				ui.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {

						btn_decoderConfig.setText(decodera.getCurrentConfigName());
					}
				});
				ui.setVisible(true);
			} else if ("openEncoder".equals(cmd)) {
				String txt = btn_encoderConfig.getText();

				final MsgEncoder encodera = CommonTools.getEncoderByName(encoder.getSelectedItem().toString());
				JDialog ui = encodera.editEncoderConfigDialog(txt);
				ui.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						btn_encoderConfig.setText(encodera.getCurrentConfigName());
					}
				});
				ui.setVisible(true);
			}

		}

	}
}
