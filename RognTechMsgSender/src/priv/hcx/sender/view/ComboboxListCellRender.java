package priv.hcx.sender.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;


public 
class ComboboxListCellRender extends DefaultListCellRenderer {
	private static ComboboxListCellRender inst=new ComboboxListCellRender();
	private ComboboxListCellRender(){
		
	}
	public static DefaultListCellRenderer getInst(){
		return inst;
	}
	private static final long serialVersionUID = -3219293923049578105L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel ret = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof JButton) {
			ret.setText(((JButton) value).getText());
		}
		return ret;
	}

}
