package priv.hcx.sender.msg.decoder.impl;

import javax.swing.JDialog;

import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.XmlConfigDao;
import priv.hcx.sender.msg.decoder.impl.ui.XmlConfigUI;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.SenderMainFrame;

public class XmlDecoder implements MsgDecoder {
	@Override
	public String getDecoderName() {
		return "XmlDecoder";
	}

	@Override
	public Message decodeMsg(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeMsgForDisplay(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeMsgFormatedForDisplay(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}
	XmlConfigBean  conf =null;
	@Override
	public JDialog  editDecoderConfigDialog(String config) {
		XmlConfigUI dialog=new XmlConfigUI(SenderMainFrame.getMainFrame(),true	);
		if(config!=null&&config.trim().length()>0){
			try {
				conf=CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[]{String.class,String.class}, config,"decoder");
				if(conf==null){
					conf=new XmlConfigBean();
					conf.setType("decoder");
					
				}
				
			} catch (Exception e) {
				conf=new XmlConfigBean();
				conf.setType("decoder");
			}
			
			
		}
		else {
			conf=new XmlConfigBean();
			conf.setType("decoder");
		}
		dialog.setConfig(conf);
		GUITool.adjustFrame(dialog, false);
		return dialog;
	}

	@Override
	public String getCurrentConfigName() {
		return conf.getName();
	}

	@Override
	public  void setCurrentConfigName(String config) {
		if(config!=null&&config.trim().length()>0){
			try {
				conf=CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[]{String.class,String.class}, config,"decoder");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
