package priv.hcx.sender.msg.editor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import priv.hcx.sender.bean.MsgField;

public class FieldTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MsgField> fields = new ArrayList<MsgField>();
	public List<MsgField>  getFields(){
		return fields;
	}
	@Override
	public void removeRow(int row) {
		this.fields.remove(row);
	}

	@Override
	public int getColumnCount() {

		return 3;
	}

	private static String[] columns = { "名字", "描述", "类型" };

	@Override
	public String getColumnName(int column) {

		return columns[column];
	}

	@Override
	public boolean isCellEditable(int row, int column) {

		return true;
	}

	@Override
	public Object getValueAt(int row, int column) {
		MsgField field = fields.get(row);
		Object ret = null;
		if (field != null) {
			switch (column) {
			case 0:
				ret = field.getName();
				break;
			case 1:
				ret = field.getDesc();
				break;
			case 2:
				ret = field.getSrc();
			}
		}
		return ret;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		MsgField field = fields.get(row);
		if (field != null) {
			switch (column) {
			case 0:
				field.setName(aValue.toString());
				break;
			case 1:
				field.setDesc(aValue.toString());
				break;
			case 2:
				field.setSrc(aValue.toString());
			}
		}
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if (fields == null)
			fields = new ArrayList<MsgField>();
		return this.fields.size();
	}

	public void addRow(MsgField field) {
		this.fields.add(field);
		// this.fireTableRowsInserted(this.fields.size()-1,
		// this.fields.size()-1);
		// this.fireTableDataChanged();
		this.fireTableDataChanged();
	}

	public MsgField getFieldAt(int index) {
		if (index < 0)
			return null;
		return this.fields.get(index);
	}
	
}
