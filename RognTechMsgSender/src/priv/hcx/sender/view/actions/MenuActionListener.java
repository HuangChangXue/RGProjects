package priv.hcx.sender.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener  {
	static private MenuActionListener inst= new MenuActionListener();
	 private MenuActionListener(){}
	public static MenuActionListener getInst(){
		return inst;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0);
	}


}
