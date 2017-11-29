package priv.hcx.sender.view.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.msg.editor.MsgEditor;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.tool.MessageHelper;
import priv.hcx.sender.view.SenderMainFrame;

public class TreeNodeListerner extends MouseAdapter {
	JTree tree = null;
	JTreePopupMenu menu = null;

	public TreeNodeListerner(JTree tree) {
		this.tree = tree;
		menu = new JTreePopupMenu(tree);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			tree.setSelectionPath(tree.getClosestPathForLocation(e.getX(), e.getY()));
			this.menu.show(e.getX(), e.getY());
		} else {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {

				TreePath path = tree.getSelectionPath();

				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

				DefaultMutableTreeNode selected;
				if (path == null) {
					selected = root;
				} else {
					Object sel = path.getLastPathComponent();
					selected = (DefaultMutableTreeNode) sel;
				}
				Object bean = GUITool.treeNodeMapping.get(selected);
				if (bean != null) {
					if (Transaction.class.isAssignableFrom(bean.getClass())) {
						Transaction tran = (Transaction) bean;
						System.out.println(tran.getId());
						MsgEditor editor = CommonTools.loadService(MsgEditor.class).get(0);
						MsgField.initIdx();
						MessageHelper.setCurrentSelectedTransaction(tran);
						SenderMainFrame.getMainFrame().setMessageEditor(editor.getEditorPanel(tran.getId()));
					} else {
						MessageHelper.setCurrentSelectedTransaction(null);
					}
				}
			} else {
				MessageHelper.setCurrentSelectedTransaction(null);

			}

		}
	}
}
