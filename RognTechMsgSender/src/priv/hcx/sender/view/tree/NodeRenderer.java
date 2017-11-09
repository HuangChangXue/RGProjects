package priv.hcx.sender.view.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.ibatis.io.Resources;

public class NodeRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color background = null;

	public NodeRenderer(Color background) {
		this.background = background;
	}

	Icon nodeicon =createIcon();
	  private Icon  createIcon() {  
	        // TODO Auto-generated method stub  
		  int w=20;int h=20;
	        BufferedImage iconImage = new BufferedImage(w, h,  
	                BufferedImage.TYPE_4BYTE_ABGR);  
	        Graphics2D g2 = iconImage.createGraphics();  
	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        try {
				g2.drawImage(ImageIO.read(Resources.getResourceAsStream("priv/hcx/sender/init/res/t6.png")), 0, 0, w, h, null);
			     g2.dispose();  
			     return new ImageIcon(iconImage);  
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        return this.getLeafIcon();
	   
	    }  
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		setForeground(Color.BLACK);
		setTextSelectionColor(Color.RED);
		setBackgroundSelectionColor(Color.CYAN);
		setBackgroundNonSelectionColor(background);

		Icon icon = null;
		if (!node.isLeaf()) {
			if (expanded) {
				icon = getOpenIcon();
			} else {
				icon = getClosedIcon();
			}
		}
		else {
			icon=nodeicon;
		}
		this.setIcon(icon);

		return this;
	}
}