package priv.hcx.sender.msg.header.impl.afaheader;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ArteryHeaderEditor extends JDialog {
	String name = null;

	public ArteryHeaderEditor(String name) {
		setResizable(false);
		this.name = name;
		this.setTitle(name);
		getContentPane().setLayout(null);
	}
}