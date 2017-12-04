package priv.hcx.sender.msg.decoder.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.decoder.impl.bean.Hex2StringConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.Hex2StringConfigDao;
import priv.hcx.sender.msg.decoder.impl.ui.Hex2StringConfigUI;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.tool.HEX2BIN;
import priv.hcx.sender.view.SenderMainFrame;

public class Hex2StringDecoder implements MsgDecoder {

	@Override
	public String getDecoderName() {
		return "Hex2StringDecoder";
	}

	@Override
	public Message decodeMsg(byte[] data) {
		String ret=new String(data);
		Message msg=new Message();
		MsgHead head=new MsgHead();
		MsgBody body=new MsgBody();
		List<MsgField> fields=new ArrayList<MsgField>();
		String[] sFields=ret.split(";");
		for(String s:sFields){
			MsgField field=new MsgField();
			fields.add(field);
			String [] keyValue=s.split("-");
			field.setName(keyValue[0]);
			field.setValue(keyValue[1]);
		}
		body.setFields(fields);
		MsgTail tail=new MsgTail();
		msg.setBody(body);
		msg.setTail(tail);
		msg.setHead(head);
		return msg;
	}

	private byte[] encodeMsg(Message msg) {
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
	public String decodeMsgForDisplay(Message msg) {
		String ret=HEX2BIN.encode(encodeMsg(msg));
		return ret;
	}

	@Override
	public String decodeMsgFormatedForDisplay(Message msg) {
		String value=decodeMsgForDisplay(msg);
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
	public JDialog  editDecoderConfigDialog(String config) {
		bean=null;
		Hex2StringConfigUI ui=new Hex2StringConfigUI(SenderMainFrame.getMainFrame(),true	);
		
//		ui.setVisible(true);
		if(config!=null){
			try {
				 bean=CommonTools.doDBQueryOperationSingle(Hex2StringConfigDao.class, "selectByName", Hex2StringConfigBean.class, new Class[]{String.class,String.class}, config	,"decoder");
				if(bean==null){
					bean=new Hex2StringConfigBean();
					bean.setType("decoder");
				}
				ui.setConfig(bean);
			} catch (Exception e) {
				
			}
		}
		else {
			
			bean=new Hex2StringConfigBean();
			bean.setType("decoder");
			ui.setConfig(bean);
		}
		GUITool.adjustFrame(ui, false);
		return ui;
	}

	@Override
	public String getCurrentConfigName() {
		
		return bean.getName();
	}

	@Override
	public void  setCurrentConfigName(String config) {
		bean=null;
		if(config!=null){
			try {
				 bean=CommonTools.doDBQueryOperationSingle(Hex2StringConfigDao.class, "selectByName", Hex2StringConfigBean.class, new Class[]{String.class,String.class}, config,"decoder"	);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
