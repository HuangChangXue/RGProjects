package priv.hcx.sender.msg.decoder.impl.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import priv.hcx.sender.msg.decoder.impl.bean.Hex2StringConfigBean;
import priv.hcx.sender.msg.decoder.impl.dao.Hex2StringConfigDao;
import priv.hcx.sender.tool.CommonTools;

public class Hex2StringConfigUI extends JDialog {
	private JTextField txt_ConfigName;
	JComboBox combo_charsetEncoding = new JComboBox();JButton btn_save = new JButton("保存");
	
	public Hex2StringConfigUI( JFrame parent,boolean isModel) {
		super(parent,isModel);
		this.setSize(277, 176);
		
		this.setResizable(false);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		CmdActionListener list=new CmdActionListener();
		
		getContentPane().add(label, "3, 2");
		
		getContentPane().add(combo_List, "6, 2, fill, default");
		
		JLabel lblNewLabel = new JLabel("配置名称");
		getContentPane().add(lblNewLabel, "3, 4, center, default");
		
		txt_ConfigName = new JTextField();
		getContentPane().add(txt_ConfigName, "6, 4, fill, default");
		txt_ConfigName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("字符编码");
		getContentPane().add(lblNewLabel_1, "3, 6, center, default");
		
		combo_charsetEncoding.setModel(new DefaultComboBoxModel(new String[] {"UTF-8", "GBK", "GB2312", "GB18030"}));
		getContentPane().add(combo_charsetEncoding, "6, 6, fill, default");
		
		
		getContentPane().add(btn_save, "6, 10, right, center");
		btn_save.setActionCommand("save");
		btn_save.addActionListener(list);
		
	}
	Hex2StringConfigBean conf=null;
	private final JLabel label = new JLabel("配置列表");
	private final JComboBox combo_List = new JComboBox();
	public void setConfig(Hex2StringConfigBean configBean){
		conf=configBean;
		txt_ConfigName.setText(conf.getName());
		combo_charsetEncoding.setSelectedItem(conf.getEncoding());
		combo_List.removeAllItems();
		combo_List.addItem("");
		combo_List.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					String name=e.getItem().toString();
					if(name!=null&&name.trim().length()>0){
						try {
							Hex2StringConfigBean	tmp=CommonTools.doDBQueryOperationSingle(Hex2StringConfigDao.class, "selectByName", Hex2StringConfigBean.class, new Class[]{String.class,String.class}, name,conf.getType());
							conf.setName(tmp.getName());
							conf.setEncoding(tmp.getEncoding());
							txt_ConfigName.setText(conf.getName());
							combo_charsetEncoding.setSelectedItem(conf.getEncoding());
						} catch (Exception e1) {
							
						}
					}
				}
			}
			
		});
		List<Hex2StringConfigBean> configs;
		try {
			configs = CommonTools.doDBQueryOperation(Hex2StringConfigDao.class, "selectAll", Hex2StringConfigBean.class, new Class[]{String.class}, configBean.getType());
			if(configs!=null&&configs.size()>0){
				for(Hex2StringConfigBean config:configs){
					combo_List.addItem(config.getName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class CmdActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd=e.getActionCommand();
			if("save".equals(cmd)){
				if(conf.getName()==null){
					conf.setEncoding(combo_charsetEncoding.getSelectedItem().toString());
					conf.setName(txt_ConfigName.getText());
					try {
						CommonTools.doDBSaveOrUpdateOperation(Hex2StringConfigDao.class, "save", new Class[]{Hex2StringConfigBean.class},conf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					conf.setEncoding(combo_charsetEncoding.getSelectedItem().toString());
					conf.setName(txt_ConfigName.getText());
					try {
						CommonTools.doDBSaveOrUpdateOperation(Hex2StringConfigDao.class, "update", new Class[]{Hex2StringConfigBean.class},conf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}


}
