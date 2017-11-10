package priv.hcx.sender.msg.editor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.msg.field.editor.FieldEditor;
import priv.hcx.sender.msg.field.editor.impl.ConstFieldProvider;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.view.SenderMainFrame;

public class TableRowSelectionListener implements ListSelectionListener {
	private JTable table;

	private TableRowSelectionListener(JTable table) {
		this.table = table;
	}

	public static ListSelectionListener getInst(JTable table) {
		return new TableRowSelectionListener(table);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int viewIdx = table.getSelectedRow();
			int selectedrow = table.convertRowIndexToModel(viewIdx);
			FieldTableModel model = (FieldTableModel) table.getModel();
			List<FieldEditor> editors = CommonTools.loadService(FieldEditor.class);
			List<FieldEditor> aviliable = new ArrayList<FieldEditor>();
			MsgField field = model.getFieldAt(selectedrow);
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
		}

	}

}
