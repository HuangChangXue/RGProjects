package priv.hcx.sender.msg.field.editor.impl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigDao;
import priv.hcx.sender.msg.field.editor.impl.bean.RandomConfigBean;
import priv.hcx.sender.tool.CommonTools;
import net.miginfocom.swing.MigLayout;

public class RandomFieldEditor extends JPanel {
	private final JCheckBox chkNum = new JCheckBox("数字");
	private JTextField txt_numLen;
	private JTextField txt_charLowLen;
	private JTextField txt_charCapLen;
	private JTextField txt_charOtherLen;

	JCheckBox chkCharCap = new JCheckBox("大写字母");
	JCheckBox chkCharOther = new JCheckBox("其它字符");
	JCheckBox chkCharLow = new JCheckBox("小写字母");
	public RandomFieldEditor() {
		bean=null;
		setLayout(new BorderLayout(0, 0));
		BtnActionListener list=new BtnActionListener(this);
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[49px][97px][97px,grow][73px,grow][48px,grow][29px][57px]", "[23px][]"));

		JButton button = new JButton("预览");
		button.setActionCommand("preview");

		JLabel lblNewLabel_1 = new JLabel("组成：");
		panel.add(lblNewLabel_1, "cell 0 0,growx");
		panel.add(chkNum, "cell 1 0,alignx left,aligny center");

		panel.add(chkCharLow, "cell 2 0,alignx left,aligny center");

		panel.add(chkCharCap, "cell 3 0,alignx left,aligny center");

		panel.add(chkCharOther, "cell 4 0,alignx left,aligny center");
		panel.add(button, "cell 6 0,growx,aligny center");

		JLabel lblNewLabel = new JLabel("长度：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel, "cell 0 1,growx,aligny center");

		txt_numLen = new JTextField();
		panel.add(txt_numLen, "cell 1 1,growx,aligny center");
		txt_numLen.setColumns(10);

		txt_charLowLen = new JTextField();
		txt_charLowLen.setColumns(10);
		panel.add(txt_charLowLen, "cell 2 1,growx,aligny center");

		txt_charCapLen = new JTextField();
		txt_charCapLen.setColumns(10);
		panel.add(txt_charCapLen, "cell 3 1,growx,aligny center");

		txt_charOtherLen = new JTextField();
		txt_charOtherLen.setColumns(10);
		panel.add(txt_charOtherLen, "cell 4 1,growx,aligny center");

		JButton button_1 = new JButton("保存");
		button_1.setActionCommand("save");
		button_1.addActionListener(list);
		button.addActionListener(list);
		panel.add(button_1, "cell 6 1,growx");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		scrollPane.setViewportView(preview);
	}
	JTextArea preview = new JTextArea();
	private RandomConfigBean bean=null;
	public void setConfig(RandomConfigBean bean2) {
		this.bean=bean2;
		this.chkNum.setSelected(bean.isContainNum());
		this.chkCharCap.setSelected(bean2.isContainCharCap());
		this.chkCharLow.setSelected(bean.isContainCharLow());
		this.chkCharOther.setSelected(bean2.isContainCharOther());
		this.txt_charCapLen.setText(bean.getCharCapLen());
		this.txt_charLowLen.setText(bean.getCharLowLen());
		this.txt_charOtherLen.setText(bean.getCharOtherLen());
		this.txt_numLen.setText(bean.getNumLen());
		
	}

class BtnActionListener implements ActionListener {
	private RandomFieldEditor editor = null;

	BtnActionListener(RandomFieldEditor eidtor) {
		this.editor = eidtor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//			chkNum.setSelected(bean.isContainNum());
		bean.setContainNum(chkNum.isSelected());
//		chkCharCap.setSelected(bean2.isContainCharCap());
		bean.setContainCharCap(chkCharCap.isSelected());
//		chkCharLow.setSelected(bean.isContainCharLow());
		bean.setContainCharLow(chkCharLow.isSelected());
//		this.chkCharOther.setSelected(bean2.isContainCharOther());
		bean.setContainCharOther(chkCharOther.isSelected());
//		this.txt_charCapLen.setText(bean.getCharCapLen());
		bean.setCharCapLen(txt_charCapLen.getText());
//		this.txt_charLowLen.setText(bean.getCharLowLen());
		bean.setCharLowLen(txt_charLowLen.getText());
//		this.txt_charOtherLen.setText(bean.getCharOtherLen());
		bean.setCharOtherLen(txt_charOtherLen.getText());
//		this.txt_numLen.setText(bean.getNumLen());
		bean.setNumLen(txt_numLen.getText());
		if ("save".equals(e.getActionCommand())) {
			try {
				RandomConfigBean tmp=CommonTools.doDBQueryOperationSingle(RandomConfigDao.class, "queryById", RandomConfigBean.class, new Class[]{String.class}, bean.getId());
				
				if (tmp==null){
					CommonTools.doDBSaveOrUpdateOperation(RandomConfigDao.class, "save", new Class[]{RandomConfigBean.class}, bean);
				}
				else {
					CommonTools.doDBSaveOrUpdateOperation(RandomConfigDao.class, "update", new Class[]{RandomConfigBean.class}, bean);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("preview".equalsIgnoreCase(e.getActionCommand())) {
			//TODO    createRandom value
		}
	}
}

}
