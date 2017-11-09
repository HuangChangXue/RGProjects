package priv.hcx.sender.msg.header.impl.afaheader;

import javax.swing.JFrame;

public class ArteryHeaderEditor extends JFrame{
	String  name=null;
	public ArteryHeaderEditor(String name) {
		this.name=name;
		this.setTitle(name);
		getContentPane().setLayout(null);
	}
}