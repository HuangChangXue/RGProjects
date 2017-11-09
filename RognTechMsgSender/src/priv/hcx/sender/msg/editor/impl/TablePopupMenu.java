package priv.hcx.sender.msg.editor.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.Const;

public class TablePopupMenu implements priv.hcx.sender.view.Const {
	JTable table = null;
	String transid;
	public TablePopupMenu(JTable table,String transactionid) {
		this.table = table;
		this.transid=transactionid;
		JPopupMenu menu = new JPopupMenu();
		menu.add(createMenuItem(MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_NEW));
		menu.add(createMenuItem(MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_DEL));
		table.setComponentPopupMenu(menu);

	}

	private JMenuItem createMenuItem(String title) {
		JMenuItem menuItem = new JMenuItem(GUITool.getName(title));
		menuItem.setActionCommand(title);
		menuItem.addActionListener(new TablePopupMenuListener(table,transid));
		return menuItem;
	}
}

class TablePopupMenuListener implements ActionListener, Const {
	JTable table = null;
private String transid=null;
	public TablePopupMenuListener(JTable table,String transactionid) {
		this.table = table;
		this.transid=transactionid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_DEL.equals(cmd)) {
			FieldTableModel model = (FieldTableModel) table.getModel();
			int[] sel = table.getSelectedRows();
			for (int i : sel) {
				model.removeRow(table.convertColumnIndexToModel(i));
			}

		} else if (MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_NEW.equals(cmd)) {
			FieldTableModel model = (FieldTableModel) table.getModel();
			model.addRow(new MsgField(transid));
		}
	}

}
