package priv.hcx.sender.msg.encoder.impl;

import java.util.List;

import javax.swing.JDialog;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.msg.decoder.impl.bean.Hex2StringConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.Hex2StringConfigDao;
import priv.hcx.sender.msg.decoder.impl.ui.Hex2StringConfigUI;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.tool.HEX2BIN;
import priv.hcx.sender.view.SenderMainFrame;

public class String2HexEncoder implements MsgEncoder {

	@Override
	public String getEncoderName() {

		return "String2Hex";
	}


	@Override
	public byte[] encodeMsg(Message msg) {
		MsgHead head=msg.getHead();
		MsgBody body=msg.getBody();
		List<MsgField> fields=	body.getFields();
		MsgTail tail=msg.getTail();
		StringBuilder sb=new StringBuilder();
		for(int i =0;i<fields.size();++i){
			MsgField field=fields.get(i);
			sb.append(field.getName()).append("-").append(field.getValue()).append(";");
		}
		return sb.toString().getBytes();
	}

	@Override
	public String encodeMsgForDisplay(Message msg) {
		byte[] data=encodeMsg(msg);
		String ret=HEX2BIN.encode(data);
		return ret;
	}
	@Override
	public String encodeMsgFormatedForDisplay(Message msg) {
		String value=encodeMsgForDisplay(msg);
		StringBuilder sb=new StringBuilder();
		for( int i=0;i<value.length();i+=2){
			if(i%32==0){
				sb.append("\n");
			}
			sb.append(value.subSequence(i, i+2));
			sb.append(" ");
			
		}
		return  sb.toString();
	}

	Hex2StringConfigBean bean=new Hex2StringConfigBean();
	public JDialog  editEncoderConfigDialog(String config) {
		bean=null;
		Hex2StringConfigUI ui=new Hex2StringConfigUI(SenderMainFrame.getMainFrame(),true	);
	
		
		if(config!=null){
			try {
				 bean=CommonTools.doDBQueryOperationSingle(Hex2StringConfigDao.class, "selectByName", Hex2StringConfigBean.class, new Class[]{String.class}, config	);
				if(bean==null){
					bean=new Hex2StringConfigBean();
					bean.setType("encoder");
				}
				ui.setConfig(bean);
			} catch (Exception e) {
				e.printStackTrace();
				if(bean==null){
					bean=new Hex2StringConfigBean();
					bean.setType("encoder");
				}
				ui.setConfig(bean);
			}
		}
		else {
			
			bean=new Hex2StringConfigBean();
			bean.setType("encoder");
			ui.setConfig(bean);
		}
		GUITool.adjustFrame(ui, false);
		return ui;
	}

	@Override
	public String getCurrentConfigName() {
		
		return bean.getName();
	}
	
}
