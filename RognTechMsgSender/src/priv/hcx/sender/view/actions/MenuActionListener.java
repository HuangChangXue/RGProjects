package priv.hcx.sender.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import priv.hcx.sender.db.ui.DataBaseConnectionEditorUI;
import priv.hcx.sender.server.ui.ServerConfUI;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.Const;
import priv.hcx.sender.view.SenderMainFrame;

public class MenuActionListener implements ActionListener ,Const {
	static private MenuActionListener inst= new MenuActionListener();
	 private MenuActionListener(){}
	public static MenuActionListener getInst(){
		return inst;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd=arg0.getActionCommand();
		if(cmd.equals(MAIN_WINDOW_MENU_CONFIG_DATABASE_NEW)){
		JDialog frame=new 	DataBaseConnectionEditorUI(SenderMainFrame.getMainFrame(),true	);
		GUITool.adjustFrame(frame, false);
		frame.setVisible(true);
		//		frame.show();
//			GUITool.adjustFrame(frame,false);
//			frame.setVisible(true);
//			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else if (cmd.equals(MAIN_WINDOW_MENU_CONFIG_SERVER_ADD)){
			JDialog frame=new ServerConfUI(SenderMainFrame.getMainFrame());
			GUITool.adjustFrame(frame, false);
			frame.setVisible(true);
		}
	}


}
