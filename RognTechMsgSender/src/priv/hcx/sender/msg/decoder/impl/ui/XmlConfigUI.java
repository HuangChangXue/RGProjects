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
import javax.swing.JButton;

import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;

public class XmlConfigUI extends JDialog {
	private JTextField txtRootTagName;
	private JTextField txtFieldTagName;
	private JTextField txtFieldAttrName;
	private JTextField txtFieldValueAttrName;
	JComboBox comboConfigList = new JComboBox();
	JComboBox comboEncoding = new JComboBox();

	JRadioButton rdbtnRootAsTranName = new JRadioButton("交易名称");
	JRadioButton rdbtnRootAsRoot = new JRadioButton("ROOT");
	JRadioButton rdbtnRootAsSelfDefined = new JRadioButton("自定义");

	JRadioButton rdbtnFieldAsTagName = new JRadioButton("字段名作为标签名");
	JRadioButton rdbtnFieldAsAttr = new JRadioButton("字段名作为属性值");

	JRadioButton rdbtnFieldValueAsText = new JRadioButton("值作为节点文本");
	JRadioButton radioFieldValueAsAttr = new JRadioButton("值作为属性值");

	public XmlConfigUI(JFrame parent,boolean isModel ) {
		super(parent,isModel);
		comboEncoding.setModel(new DefaultComboBoxModel(new String[] {"UTF-8", "GBK", "GB2312", "GB18030"}));
		ButtonGroup rootGroup = new ButtonGroup();
		this.setSize(531, 362);
		this.setPreferredSize(this.getSize());
		rootGroup.add(rdbtnRootAsTranName);
		rootGroup.add(rdbtnRootAsRoot);
		rootGroup.add(rdbtnRootAsSelfDefined);

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

		JLabel label_2 = new JLabel("属性名：");
		getContentPane().add(label_2, "8, 20, left, default");

		txtFieldValueAttrName = new JTextField();
		txtFieldValueAttrName.setColumns(10);
		getContentPane().add(txtFieldValueAttrName, "10, 20, fill, default");
		
				JButton btnSave = new JButton("保存");
				getContentPane().add(btnSave, "14, 22");
	}
	public void setConfig(XmlConfigBean conf){
		
	}
}
