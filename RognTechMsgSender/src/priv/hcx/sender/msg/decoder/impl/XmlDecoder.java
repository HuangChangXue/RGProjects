package priv.hcx.sender.msg.decoder.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.Message;
import priv.hcx.sender.bean.msg.MsgBody;
import priv.hcx.sender.bean.msg.MsgHead;
import priv.hcx.sender.bean.msg.MsgTail;
import priv.hcx.sender.msg.decoder.MsgDecoder;
import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.XmlConfigDao;
import priv.hcx.sender.msg.decoder.impl.ui.XmlConfigUI;
import priv.hcx.sender.msg.encoder.impl.XmlEncoder;
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
		Message msg = new Message();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new ByteArrayInputStream(data));
			Element root = doc.getRootElement();
			Element head = root.element("head");
			Element body = root.element("body");
			Element tail = root.element("tail");
			List<Element> eles = body.elements();
			
			msg.setHead(new MsgHead());
			msg.setTail(new MsgTail());
			MsgBody bodyfi = new MsgBody();
			msg.setBody(bodyfi);
			List<MsgField> fields = new ArrayList<MsgField>();
			bodyfi.setFields(fields);
			for (Element e : eles) {
				fields.add(getField(e));
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}

	private MsgField getField(Element e) {
		MsgField field = new MsgField();
		if (conf == null || conf.getFieldNameType() == null) {
			field.setName(e.getName());
		} else {
			if ("AsTagName".equals(conf.getFieldNameType())) {
				field.setName(field.getName());
			} else {
				// e=par.addElement(conf.getFieldNameTagName());
				// e.addAttribute(conf.getFielNameAttrName(),
				// field.getName()==null?"":field.getName().toString());
				Attribute attr = e.attribute(conf.getFielNameAttrName());
				field.setName(attr.getValue());
			}
			if (conf == null || conf.getFieldValueType() == null) {
				field.setValue(e.getText());
			} else {
				if ("AsText".equals(conf.getFieldValueType())) {
					field.setValue(e.getText());
				} else {
					field.setValue(e.attributeValue(conf.getFieldValueAttrName()));
				}
			}
		}
		return field;
	}

	@Override
	public String decodeMsgForDisplay(Message msg) {
		endocer.setCurrentConfigName(conf.getName());
		return endocer.encodeMsgForDisplay(msg);
	}

	@Override
	public String decodeMsgFormatedForDisplay(Message msg) {
		endocer.setCurrentConfigName(conf.getName());
		return endocer.encodeMsgFormatedForDisplay(msg);
		// TODO Auto-generated method stub
//		return null;
	}

	XmlConfigBean conf = null;
	XmlEncoder endocer=new XmlEncoder();
	@Override
	public JDialog editDecoderConfigDialog(String config) {
		XmlConfigUI dialog = new XmlConfigUI(SenderMainFrame.getMainFrame(), true);
		if (config != null && config.trim().length() > 0) {
			try {
				conf = CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[] { String.class, String.class }, config, "decoder");
				if (conf == null) {
					conf = new XmlConfigBean();
					conf.setType("decoder");

				}

			} catch (Exception e) {
				conf = new XmlConfigBean();
				conf.setType("decoder");
			}

		} else {
			conf = new XmlConfigBean();
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
	public void setCurrentConfigName(String config) {
		if (config != null && config.trim().length() > 0) {
			try {
				conf = CommonTools.doDBQueryOperationSingle(XmlConfigDao.class, "selectByName", XmlConfigBean.class, new Class[] { String.class, String.class }, config, "decoder");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
