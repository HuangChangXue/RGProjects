package priv.hcx.sender.msg.editor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import priv.hcx.sender.bean.MsgField;

public class FieldTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer ,MsgField> fields = new HashMap<Integer,MsgField>();
	public List<MsgField>  getFields(){
		List<MsgField> ret=new ArrayList<MsgField>();
		for(MsgField field:fields.values()){
			ret.add(field);
		}
		return ret;
	}
	@Override
	public void removeRow(int row) {
		this.fields.remove(row);
	}

	@Override
	public int getColumnCount() {

		return columns.length;
	}

	private static String[] columns = { "名字", "描述", "类型","顺序号" };

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
			break;
			case 3:
				ret=field.getOrderIdx();
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
				field.setSrc(aValue.toString());break;
			case 3:
				field.setOrderIdx(Integer.valueOf(aValue+""));
			}
		}
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if (fields == null)
			fields = new HashMap<Integer,MsgField>();
		return this.fields.size();
	}

	public void addRow(MsgField field) {
		this.fields.put(fields.size(),field);
	}

	public MsgField getFieldAt(int index) {
		if (index < 0)
			return null;
		return this.fields.get(index);
	}
	
}
