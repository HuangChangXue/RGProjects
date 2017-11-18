package priv.hcx.sender.msg.field.editor.impl.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTable;

import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigBean;
import priv.hcx.sender.msg.field.editor.impl.bean.ConstConfigDao;
import priv.hcx.sender.tool.CommonTools;
import net.miginfocom.swing.MigLayout;

public class ConstFieldEditor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	JCheckBox radio_isallowNull = new JCheckBox("允许为空");
	JCheckBox radio_isAllowNewLine = new JCheckBox("允许换行");
	JButton btn_createRandomValue = new JButton("生成随机值");
	JButton btn_save = new JButton("保存");
	JTextArea txt_value = new JTextArea();

	private ConstConfigBean config = null;

	public ConstFieldEditor() {
		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(323, 376));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[73px][73px][93px][]", "[23px]"));
		// panel.setLayout(null);

		BtnActionListener list = new BtnActionListener(this);

		btn_createRandomValue.setActionCommand("rand");
		btn_createRandomValue.addActionListener(list);
		btn_save.setActionCommand("save");
		btn_save.addActionListener(list);

		panel.add(radio_isallowNull, "cell 0 0,alignx left,aligny top");
		radio_isallowNull.setBounds(36, 5, 73, 23);

		radio_isAllowNewLine.setBounds(114, 5, 73, 23);
		panel.add(radio_isAllowNewLine, "cell 1 0,alignx left,aligny top");
		// JTextArea textArea = new JTextArea();
		// add(textArea, BorderLayout.CENTER);
		btn_createRandomValue.setActionCommand("radio");
		panel.add(btn_createRandomValue, "cell 2 0,alignx left,aligny top");
		/*
		 * button.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * textArea.setText(CommonTools.createRandomString(10)); } });
		 */

		// button.setVerticalAlignment(SwingConstants.BOTTOM);
		btn_createRandomValue.setBounds(65, 33, 93, 23);
		btn_save.setActionCommand("save");
		panel.add(btn_save, "cell 3 0");

		JScrollPane scrollPane = new JScrollPane(txt_value);
		add(scrollPane, BorderLayout.CENTER);

		// table = new JTable();
		// JScrollPane jsp = new JScrollPane();
		// jsp.add(table);
		// add(jsp, BorderLayout.SOUTH);
	}

	public void setConfig(ConstConfigBean config) {
		this.config = config;
		this.radio_isAllowNewLine.setEnabled(config.getIsAllowNewLine());
		this.radio_isallowNull.setEnabled(config.getIsAllowEmpty());
		this.txt_value.setText(config.getValue());
	}

	public ConstConfigBean getConfig() {
		return config;
	}

}

class BtnActionListener implements ActionListener {
	private ConstFieldEditor editor = null;

	BtnActionListener(ConstFieldEditor eidtor) {
		this.editor = eidtor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("AAAA:" + e.getActionCommand());
		ConstConfigBean bean = this.editor.getConfig();
		bean.setIsAllowEmpty(this.editor.radio_isallowNull.isEnabled());
		bean.setIsAllowNewLine(this.editor.radio_isAllowNewLine.isEnabled());
		bean.setValue(this.editor.txt_value.getText());
		if ("save".equals(e.getActionCommand())) {
			try {
				ConstConfigBean tmp=CommonTools.doDBQueryOperationSingle(ConstConfigDao.class, "queryById", ConstConfigBean.class, new Class[]{String.class}, bean.getId());
				
				if (tmp==null){
					CommonTools.doDBSaveOrUpdateOperation(ConstConfigDao.class, "saveMsg", new Class[]{ConstConfigBean.class}, bean);
				}
				else {
					CommonTools.doDBSaveOrUpdateOperation(ConstConfigDao.class, "update", new Class[]{ConstConfigBean.class}, bean);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ("radio".equalsIgnoreCase(e.getActionCommand())) {
			this.editor.txt_value.setText(CommonTools.createRandomID());
		}
	}
}
