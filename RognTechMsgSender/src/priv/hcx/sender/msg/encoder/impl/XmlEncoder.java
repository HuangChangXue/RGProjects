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

	private Document createDocument(Message msg) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		Element head = root.addElement("head");
		Element body = root.addElement("body");
		List<MsgField> fields = msg.getBody().getFields();
		for (int i = 0; i < fields.size(); ++i) {
			MsgField field = fields.get(i);
			Element el = body.addElement(field.getName());
			// el.addAttribute("desc", field.getDesc());
			// el.addAttribute("id", field.getId());
			el.setText(field.getValue() == null ? "" : field.getValue().toString());
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
				conf = CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[] { String.class }, config);
				if (conf == null) {
					conf = new XmlConfigBean();
					conf.setType("encoder");

				}
			} catch (Exception e) {

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
		// TODO Auto-generated method stub
		return null;
	}

}
