package priv.hcx.sender.msg.encoder.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JDialog;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.XmlConfigDao;
import priv.hcx.sender.msg.decoder.impl.ui.XmlConfigUI;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.tool.HEX2BIN;
import priv.hcx.sender.view.SenderMainFrame;

public class XmlEncoder implements MsgEncoder {

	@Override
	public String getEncoderName() {
		return "XMLEncoder";
	}
	private String getRootTagName(){
		if(conf==null)return "root";
		if(conf.getRootType().equals("AsTrans")){
			return "TRANS";
		}
		else if(conf.getRootType().equals("AsRoot")){
			return "ROOT";
		}
		else if(conf.getRootType().equals("AsSelf")){
			return conf.getRootTagName();
		}
		return "root";
	}
	private Element  getEle(Element par ,MsgField field){
		Element e=null;
		if(conf==null||conf.getFieldNameType()==null){
			e=par.addElement(field.getName());
		}
		else {
			
			if("AsTagName".equals(conf.getFieldNameType())){
				e=par.addElement(field.getName());
			}
			else {
				e=par.addElement(conf.getFieldNameTagName());
				e.addAttribute(conf.getFielNameAttrName(), field.getName()==null?"":field.getName().toString());
			}
		}
		if(conf==null||conf.getFieldValueType()==null){
			e.setText( field.getValue()==null?"":field.getValue().toString());
		}
		else {
			if("AsText".equals(conf.getFieldValueType())){
				e.setText( field.getValue()==null?"":field.getValue().toString());
			}
			else {
				e.addAttribute(conf.getFieldValueAttrName(),  field.getValue()==null?"":field.getValue().toString())
;			}
		}
		return e;
	}
	private Document createDocument(Message msg) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(getRootTagName());
		Element head = root.addElement("head");
		Element body = root.addElement("body");
		List<MsgField> fields = msg.getBody().getFields();
		for (int i = 0; i < fields.size(); ++i) {
			MsgField field = fields.get(i);
			this.getEle(body, field);
//			Element el = body.addElement(field.getName());
//			// el.addAttribute("desc", field.getDesc());
//			// el.addAttribute("id", field.getId());
//			el.setText(field.getValue() == null ? "" : field.getValue().toString());
		}
		Element tail = root.addElement("tail");
		return doc;
	}

	@Override
	public byte[] encodeMsg(Message msg) {
		MsgHead head = msg.getHead();
		MsgBody body = msg.getBody();
		List<MsgField> fields = body.getFields();
		MsgTail tail = msg.getTail();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.size(); ++i) {
			MsgField field = fields.get(i);
			sb.append(field.getName()).append("-").append(field.getValue()).append(";");
		}
		return sb.toString().getBytes();
	}

	@Override
	public String encodeMsgForDisplay(Message msg) {
		Document doc = this.createDocument(msg);
		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding("GBK");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			XMLWriter writer = new XMLWriter(bos, format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return bos.toString("GBK");
		} catch (UnsupportedEncodingException e) {
			return bos.toString();
		}
	}

	@Override
	public String encodeMsgFormatedForDisplay(Message msg) {

		Document doc = this.createDocument(msg);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			XMLWriter writer = new XMLWriter(bos, format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return bos.toString("GBK");
		} catch (UnsupportedEncodingException e) {
			return bos.toString();
		}

	}

	XmlConfigBean conf = null;

	@Override
	public JDialog editEncoderConfigDialog(String config) {
		XmlConfigUI dialog = new XmlConfigUI(SenderMainFrame.getMainFrame(), true);
		if (config != null && config.trim().length() > 0) {
			try {
				conf = CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[] { String.class ,String.class}, config,"encoder");
				if(conf==null){
					conf = new XmlConfigBean();
					conf.setType("encoder");
				}
			} catch (Exception e) {
				conf = new XmlConfigBean();
				conf.setType("encoder");
			}
			
		} else {
			conf = new XmlConfigBean();
			conf.setType("encoder");
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
	public void setCurrentConfigName(String config) {
		// TODO Auto-generated method stub
		if (config != null && config.trim().length() > 0) {
			try {
				conf = CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[] { String.class,String.class }, config,"encoder");
				if (conf == null) {
					conf = new XmlConfigBean();
					conf.setType("encoder");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
	}

}
