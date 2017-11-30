package priv.hcx.sender.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JSplitPane;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.protel.ProtelHandler;
import priv.hcx.sender.server.Server;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.MessageHelper;
import priv.hcx.sender.tool.ProtelHelper;

public class PreviewPanel extends JPanel {
	private final JPanel panel_1 = new JPanel();
	JButton btn_preview = new JButton("预览");
	JButton btn_synSend = new JButton("同步发送");
	JButton btn_format = new JButton("格式化");
	JButton btn_clear = new JButton("清空记录");
	JButton btn_send = new JButton("异步发送");
	JTextArea txt_send = new JTextArea();
	JTextArea txt_receive = new JTextArea();
	JComboBox<String> combo_server = new JComboBox<String>();
	List<ServerConf> servers=null;
	Map<String,ServerConf> serversKeys=new HashMap<String,ServerConf>();
	public PreviewPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[73.00px]", "[21px,center][][]"));

		panel.add(combo_server, "cell 0 0,growx,aligny center");
		
		combo_server.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent e) {
				 servers=Server.getAllServerConf();
				int selIdx=combo_server.getSelectedIndex();
				combo_server.removeAllItems();
				for(ServerConf conf:servers){
					combo_server.addItem(conf.getName());
					serversKeys.put(conf.getName(), conf);
				}
				combo_server.setSelectedIndex(selIdx);
			}
			
		});
		
		PreviewActionListener list=new PreviewActionListener();
		// btn_preview.addActionListener();
		panel.add(btn_preview, "cell 0 1,growx,aligny center");
		btn_preview.addActionListener(list);
		btn_preview.setActionCommand("preview");
		panel.add(btn_format, "cell 0 2,growx");
		btn_format.addActionListener(list);
		btn_format.setActionCommand("format");
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new MigLayout("", "[93px,center]", "[23px,center][fill][]"));

		panel_1.add(btn_clear, "cell 0 0,growx");

		btn_synSend.setActionCommand("synSend");
		btn_synSend.addActionListener(list);
		panel_1.add(btn_synSend, "cell 0 1,growx,aligny center");
		btn_send.setActionCommand("send");
		btn_send.addActionListener(list);
		panel_1.add(btn_send, "cell 0 2,growx");

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		panel_2.add(splitPane);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
//		txt_send.setLineWrap(true);
		scrollPane.setViewportView(txt_send);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(txt_receive);
	}
	class PreviewActionListener implements ActionListener {
		Message messageToSend=null,messageReceived=null;
		public void actionPerformed(ActionEvent e) {
			String cmd=e.getActionCommand();
			String serverName=combo_server.getSelectedIndex()>=0?combo_server.getSelectedItem().toString():null;
			if(serverName==null||serverName.trim().length()<=0){
				JOptionPane.showMessageDialog(combo_server, "Please choose a server", "ServerError", JOptionPane.OK_OPTION);
				return ;
			}
			
			String selectedServer=combo_server.getSelectedItem()==null?null:combo_server.getSelectedItem().toString();
			if(selectedServer==null) return ;
			ServerConf sconf=serversKeys.get(selectedServer);
			String encoderName=sconf.getEncoder();
			MsgDecoder decoder=CommonTools.getDecoderByName(sconf.getDecoder());
			MsgEncoder  encoder=CommonTools.getEncoderByName(encoderName);
			if("preview".equals(cmd)){
				messageToSend =MessageHelper.getMessage(MessageHelper.getSelectedMsgId());
				txt_send.setText(encoder.encodeMsgForDisplay(messageToSend));
			}
			else if("format".equals(cmd)){
				if(messageToSend==null){
					messageToSend =MessageHelper.getMessage(MessageHelper.getSelectedMsgId());
				}
				txt_send.setText(encoder.encodeMsgFormatedForDisplay(messageToSend));
			}
			else if ("synSend".equals(cmd)){
				if(messageToSend==null){
					messageToSend =MessageHelper.getMessage(MessageHelper.getSelectedMsgId());
				}
				ProtelHandler handler=ProtelHelper.getProtelHandler(sconf.getProtel());
				messageReceived=handler.doSendMessage(sconf, messageToSend, null);
				txt_receive.setText(decoder.decodeMsgForDisplay(messageReceived));
				}
			else if("send".equals(cmd)){
				
			}
		}
	}
}


