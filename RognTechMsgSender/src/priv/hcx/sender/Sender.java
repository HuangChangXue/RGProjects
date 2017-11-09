package priv.hcx.sender;

import javax.swing.UIManager;
import priv.hcx.sender.view.SenderMainFrame;

public class Sender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			AppInit.init();
		} catch (Exception e) {
		} 
		new SenderMainFrame();
	}

}
