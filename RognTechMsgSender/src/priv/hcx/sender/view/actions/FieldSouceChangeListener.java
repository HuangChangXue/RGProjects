package priv.hcx.sender.view.actions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JTable;

public class FieldSouceChangeListener implements ItemListener {
	JTable table = null;

	public static ItemListener getInst(JTable table){
		return new FieldSouceChangeListener(table);
	}
	private  FieldSouceChangeListener(JTable table) {
		this.table = table;
	}
	//TODO 字段选择改变
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {

		} else {
			// java.awt.event.ItemEvent[ITEM_STATE_CHANGED,item=javax.swing.JButton[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.5,border=com.sun.java.swing.plaf.windows.XPStyle$XPEmptyBorder@55f3ddb1,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=RandomFieldProvider,defaultCapable=true],stateChange=SELECTED]
			// on
			// javax.swing.JComboBox[,246,32,81x15,layout=com.sun.java.swing.plaf.windows.WindowsComboBoxUI$3,alignmentX=0.0,alignmentY=0.0,border=com.sun.java.swing.plaf.windows.XPStyle$XPFillBorder@8bd1b6a,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=javax.swing.JButton[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.5,border=com.sun.java.swing.plaf.windows.XPStyle$XPEmptyBorder@55f3ddb1,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=RandomFieldProvider,defaultCapable=true]]
			
			try {
				Method m=e.getItem().getClass().getMethod("getActionCommand", null);
				System.out.println(m.invoke(e.getItem(), null));
			}catch(Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}