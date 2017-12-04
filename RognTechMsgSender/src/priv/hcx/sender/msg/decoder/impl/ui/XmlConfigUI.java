package priv.hcx.sender.msg.decoder.impl.ui;

import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.XmlConfigDao;
import priv.hcx.sender.tool.CommonTools;

public class XmlConfigUI extends JDialog {
	private JTextField txtRootTagName;
	private JTextField txtFieldTagName;
	private JTextField txtFieldAttrName;
	private JTextField txtFieldValueAttrName;
	JComboBox comboConfigList = new JComboBox();
	JComboBox comboEncoding = new JComboBox();
	JButton btnSave = new JButton("保存");
	JRadioButton rdbtnRootAsTranName = new JRadioButton("交易名称");
	JRadioButton rdbtnRootAsRoot = new JRadioButton("ROOT");
	JRadioButton rdbtnRootAsSelfDefined = new JRadioButton("自定义");

	JRadioButton rdbtnFieldAsTagName = new JRadioButton("字段名作为标签名");
	JRadioButton rdbtnFieldAsAttr = new JRadioButton("字段名作为属性值");

	JRadioButton rdbtnFieldValueAsText = new JRadioButton("值作为节点文本");
	JRadioButton radioFieldValueAsAttr = new JRadioButton("值作为属性值");

	public XmlConfigUI(JFrame parent, boolean isModel) {
		super(parent, isModel);
		comboEncoding.setModel(new DefaultComboBoxModel(new String[] { "UTF-8", "GBK", "GB2312", "GB18030" }));
		ButtonGroup rootGroup = new ButtonGroup();
		this.setSize(531, 362);
		this.setPreferredSize(this.getSize());
		rootGroup.add(rdbtnRootAsTranName);
		rootGroup.add(rdbtnRootAsRoot);
		rootGroup.add(rdbtnRootAsSelfDefined);
		rdbtnRootAsSelfDefined.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (rdbtnRootAsSelfDefined.isSelected()) {
					txtRootTagName.setEditable(true);
				} else {
					txtRootTagName.setEditable(false);
				}

			}

		});

		ButtonGroup fieldNameGroup = new ButtonGroup();
		fieldNameGroup.add(rdbtnFieldAsTagName);
		fieldNameGroup.add(rdbtnFieldAsAttr);

		ButtonGroup fieldValueGroup = new ButtonGroup();
		fieldValueGroup.add(rdbtnFieldValueAsText);
		fieldValueGroup.add(radioFieldValueAsAttr);
		setResizable(false);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.MIN_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("min:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.MIN_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.MIN_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
						FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_2 = new JLabel("配置名称：");
		getContentPane().add(lblNewLabel_2, "4, 2, left, default");

		comboConfigList.setEditable(true);
		getContentPane().add(comboConfigList, "6, 2, fill, default");

		JLabel lblNewLabel_3 = new JLabel("编码:");
		getContentPane().add(lblNewLabel_3, "8, 2, right, default");

		getContentPane().add(comboEncoding, "10, 2, fill, default");

		JLabel label = new JLabel("根节点名称:");
		getContentPane().add(label, "4, 6, left, default");
		rdbtnRootAsTranName.setSelected(true);

		getContentPane().add(rdbtnRootAsTranName, "6, 6");

		getContentPane().add(rdbtnRootAsRoot, "8, 6, left, default");

		getContentPane().add(rdbtnRootAsSelfDefined, "6, 8");

		JLabel label_4 = new JLabel("标签名：");
		getContentPane().add(label_4, "8, 8, left, default");

		txtRootTagName = new JTextField();
		getContentPane().add(txtRootTagName, "10, 8, fill, default");
		txtRootTagName.setColumns(10);

		JLabel lblNewLabel = new JLabel("字段名称：");
		getContentPane().add(lblNewLabel, "4, 12, left, default");
		rdbtnFieldAsTagName.setSelected(true);

		getContentPane().add(rdbtnFieldAsTagName, "6, 12");

		getContentPane().add(rdbtnFieldAsAttr, "6, 14");
		rdbtnFieldAsAttr.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (rdbtnFieldAsAttr.isSelected()) {
					txtFieldTagName.setEditable(true);
					txtFieldAttrName.setEditable(true);
				} else {
					txtFieldTagName.setEditable(false);
					txtFieldAttrName.setEditable(false);
				}

			}
		});
		JLabel label_1 = new JLabel("标签名：");
		getContentPane().add(label_1, "8, 14, left, default");

		txtFieldTagName = new JTextField();
		getContentPane().add(txtFieldTagName, "10, 14, fill, default");
		txtFieldTagName.setColumns(10);

		JLabel label_3 = new JLabel("属性名：");
		getContentPane().add(label_3, "12, 14, center, default");

		txtFieldAttrName = new JTextField();
		txtFieldAttrName.setColumns(10);
		getContentPane().add(txtFieldAttrName, "14, 14, fill, default");

		JLabel lblNewLabel_1 = new JLabel("字段值：");
		getContentPane().add(lblNewLabel_1, "4, 18, left, default");
		rdbtnFieldValueAsText.setSelected(true);

		getContentPane().add(rdbtnFieldValueAsText, "6, 18");

		getContentPane().add(radioFieldValueAsAttr, "6, 20");
		radioFieldValueAsAttr.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (radioFieldValueAsAttr.isSelected()) {
					txtFieldValueAttrName.setEditable(true);
				} else {
					txtFieldValueAttrName.setEditable(false);
				}
			}

		});
		JLabel label_2 = new JLabel("属性名：");
		getContentPane().add(label_2, "8, 20, left, default");

		txtFieldValueAttrName = new JTextField();
		txtFieldValueAttrName.setColumns(10);
		getContentPane().add(txtFieldValueAttrName, "10, 20, fill, default");
		getContentPane().add(btnSave, "14, 22");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isNew = comboConfigList.getSelectedIndex() < 0;
				conf.setName(comboConfigList.getSelectedItem().toString());
				conf.setEncoding(comboEncoding.getSelectedItem().toString());
				if (rdbtnRootAsTranName.isSelected()) {
					conf.setRootType("AsTrans");
				} else if (rdbtnRootAsRoot.isSelected()) {
					conf.setRootType("AsRoot");
				} else if (rdbtnRootAsSelfDefined.isSelected()) {
					conf.setRootType("AsSelf");
					conf.setRootTagName(txtRootTagName.getText());
				}

				if (rdbtnFieldAsTagName.isSelected()) {
					conf.setFieldNameType("AsTagName");
				} else if (rdbtnFieldAsAttr.isSelected()) {
					conf.setFieldNameType("AsAttr");
					conf.setFieldNameTagName(txtFieldTagName.getText());
					conf.setFielNameAttrName(txtFieldAttrName.getText());
				}

				if (rdbtnFieldValueAsText.isSelected()) {
					conf.setFieldValueType("AsText");
				} else if (radioFieldValueAsAttr.isSelected()) {
					conf.setFieldValueType("AsAttr");
					conf.setFieldValueAttrName(txtFieldValueAttrName.getText());
				}
				if (isNew) {
					try {
						CommonTools.doDBSaveOrUpdateOperation(XmlConfigDao.class, "save", new Class[] { XmlConfigBean.class }, conf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						CommonTools.doDBSaveOrUpdateOperation(XmlConfigDao.class, "update", new Class[] { XmlConfigBean.class }, conf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}

		});

		comboConfigList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (configs != null && e.getItem() != null) {
						String cmd = e.getItem().toString();
						for (XmlConfigBean bean : configs) {
							if (bean.getName().equals(cmd)) {
								conf .copyBean(bean);
								configConfig(bean);
								break;
							}
						}
					}
				}

			}

		});
	}

	private void configConfig(XmlConfigBean conf) {

		comboConfigList.setSelectedItem(conf.getName());
		comboEncoding.setSelectedItem(conf.getEncoding());

		String rootype = conf.getRootType();
		if (rootype != null && rootype.trim().length() >= 0) {

			// txtRootTagName.setVisible(false);
			if (rootype.equals("AsTrans")) {
				rdbtnRootAsTranName.setSelected(true);
				txtRootTagName.setText("");
				txtRootTagName.setEditable(false);
			} else if (rootype.equals("AsRoot")) {
				rdbtnRootAsRoot.setSelected(true);
				txtRootTagName.setText("");
				txtRootTagName.setEditable(false);
			} else if (rootype.equals("AsSelf")) {
				rdbtnRootAsSelfDefined.setSelected(true);
				txtRootTagName.setText(conf.getRootTagName());
				txtRootTagName.setEditable(true);
			}
		}
		String fieldNameType = conf.getFieldNameType();
		if (fieldNameType != null && fieldNameType.trim().length() > 0) {
			if ("AsTagName".equals(fieldNameType)) {
				rdbtnFieldAsTagName.setSelected(true);
				txtFieldTagName.setText("");
				txtFieldAttrName.setText("");
				txtFieldTagName.setEditable(false);
				txtFieldAttrName.setEditable(false);
			} else if ("AsAttr".equals(fieldNameType)) {
				rdbtnFieldAsAttr.setSelected(true);
				txtFieldTagName.setEditable(true);
				txtFieldTagName.setText(conf.getFieldNameTagName());
				txtFieldAttrName.setEditable(true);
				txtFieldAttrName.setText(conf.getFieldValueAttrName());
			}
		}
		String fieldValueType = conf.getFieldValueType();
		if (fieldValueType != null && fieldValueType.trim().length() >= 0) {
			if ("AsText".equals(fieldValueType)) {
				rdbtnFieldValueAsText.setSelected(true);
				txtFieldValueAttrName.setText("");
				txtFieldValueAttrName.setEditable(false);
			} else if ("AsAttr".equals(fieldValueType)) {
				radioFieldValueAsAttr.setSelected(true);
				txtFieldValueAttrName.setEditable(true);
				txtFieldValueAttrName.setText(conf.getFieldValueAttrName());
			}

		}
	}

	XmlConfigBean conf = null;
	java.util.List<XmlConfigBean> configs;

	public void setConfig(XmlConfigBean conf) {
		this.conf = conf;
		comboConfigList.removeAllItems();
		// comboConfigList.addItem("");
		try {
			java.util.List<XmlConfigBean> beans = CommonTools.doDBQueryOperation(XmlConfigDao.class, "selectAll", XmlConfigBean.class, new Class[] { String.class }, conf.getType());
			configs = beans;
			for (XmlConfigBean bean : beans) {
				comboConfigList.addItem(bean.getName());
				if (conf.getName() != null && conf.getName().equals(bean.getName())) {
					this.conf.copyBean(conf);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configConfig(conf);

		// TODO
	}
}
