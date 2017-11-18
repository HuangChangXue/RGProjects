package priv.hcx.sender.msg.editor.impl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.view.SenderMainFrame;

public class FieldSouceChangeListener implements ItemListener {
	JTable table = null;

	public static ItemListener getInst(JTable table) {
		return new FieldSouceChangeListener(table);
	}

	private FieldSouceChangeListener(JTable table) {
		this.table = table;
	}

	// TODO 字段选择改变
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {

		} else {
			try {
				Method m = e.getItem().getClass().getMethod("getActionCommand", null);

				int viewIdx = table.getSelectedRow();
				int selectedrow = table.convertRowIndexToModel(viewIdx);
				FieldTableModel model = (FieldTableModel) table.getModel();
				List<FieldEditor> editors = CommonTools.loadService(FieldEditor.class);
				List<FieldEditor> aviliable = new ArrayList<FieldEditor>();
				MsgField field = model.getFieldAt(selectedrow);
				if (field == null)
					return;
				field.setSrc(m.invoke(e.getItem(), null).toString());
				if (selectedrow < 0) {
					return;
				}
				if (editors.size() > 0) {
					for (FieldEditor editor : editors) {
						if (editor.isAviableforName(field.getSrc())) {
							aviliable.add(editor);
						}
					}
				}

				SenderMainFrame.getMainFrame().setFieldEdirot(aviliable.get(0).getEditPaneByFieldId(field.getId()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}