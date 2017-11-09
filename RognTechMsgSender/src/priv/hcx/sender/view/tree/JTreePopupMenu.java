package priv.hcx.sender.view.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.ibatis.session.SqlSession;

import priv.hcx.sender.bean.Folder;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.bean.res.FolderDao;
import priv.hcx.sender.bean.res.TransactionDao;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.Const;

public class JTreePopupMenu implements Const {
	private JTree tree;
	JPopupMenu menu = new JPopupMenu();

	public JTreePopupMenu(JTree tree) {
		this.tree = tree;

		menu.add(createMenuItem(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TRANSACTION));
		menu.add(createMenuItem(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_DEL));
		menu.add(createMenuItem(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TYPE));
		// tree.setComponentPopupMenu(menu);
	}

	private JMenuItem createMenuItem(String title) {
		JMenuItem menuItem = new JMenuItem(GUITool.getName(title));
		menuItem.setActionCommand(title);
		menuItem.addActionListener(new JTreePopupMenuListener(tree));
		return menuItem;
	}

	public void show(int x, int y) {
		menu.show(tree, x, y);
	}
}

class JTreePopupMenuListener implements ActionListener, Const {
	private JTree tree;

	public JTreePopupMenuListener(JTree tree) {
		this.tree = tree;
	}

	private void removeNode(DefaultMutableTreeNode node) {
		if (node.isLeaf()) {
			((DefaultMutableTreeNode) (node.getParent())).remove(node);
		} else {
			int cnt = node.getChildCount();
			for (int i = cnt - 1; i >= 0; --i) {
				removeNode((DefaultMutableTreeNode) node.getChildAt(i));
			}
			((DefaultMutableTreeNode) (node.getParent())).remove(node);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		TreePath path = tree.getSelectionPath();
		String cmd = e.getActionCommand();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		DefaultMutableTreeNode selected;
		if (path == null) {
			selected = root;
		} else {
			Object sel = path.getLastPathComponent();
			selected = (DefaultMutableTreeNode) sel;
		}

		if (cmd.equals(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TRANSACTION) || cmd.equalsIgnoreCase(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TYPE)) {

			if (cmd.equals(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TYPE)) {
				Folder folder = new Folder(JOptionPane.showInputDialog(tree, GUITool.getName(INPUTBOX_NEW_FOLER_MSG), GUITool.getName(INPUTBOX_NEW_FOLER_TITLE), JOptionPane.QUESTION_MESSAGE));
				folder.createRandomId();
				if (selected.isLeaf()) {
					selected = (DefaultMutableTreeNode) selected.getParent();
					selected.add(GUITool.createTreeNode(folder));
				} else {
					selected.add(GUITool.createTreeNode(folder));
				}
				Object bean = GUITool.treeNodeMapping.get(selected);
				if (bean != null) {
					Folder par = (Folder) bean;
					folder.setParentid(par.getId());
				} else {
					folder.setParentid("root");
				}
				SqlSession session = CommonTools.getSQLSession(true);
				FolderDao dao = CommonTools.getMapper(session, FolderDao.class);
				dao.save(folder);
				dao.selectByParentId(null);
				CommonTools.closeSession(session);
			} else {
				String tranName = JOptionPane.showInputDialog(tree, GUITool.getName(INPUTBOX_NEW_TRANSACTION_MSG), GUITool.getName(INPUTBOX_NEW_TRANSACTION_TITLE), JOptionPane.QUESTION_MESSAGE);
				Transaction tran=new Transaction(tranName);
				if (selected.isLeaf()) {
					selected=(DefaultMutableTreeNode) selected.getParent();
					selected.add(GUITool.createTreeNode(tran));
				} else {
					selected.add(GUITool.createTreeNode(tran));
				}
				
				Object bean = GUITool.treeNodeMapping.get(selected);
				if (bean != null) {
					Folder par = (Folder) bean;
					tran.setFolderid(par.getId());
				} else {
					tran.setFolderid("root");
				}
				
				SqlSession session = CommonTools.getSQLSession(true);
				TransactionDao dao = CommonTools.getMapper(session, TransactionDao.class);
				dao.save(tran);
			
				CommonTools.closeSession(session);
			}
			tree.expandPath(path);
		} else if (cmd.equals(MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_DEL)) {

			if (selected.isLeaf()) {
				int option = JOptionPane.showConfirmDialog(tree, GUITool.getName(DELETE_TRANSACTION_MSG), GUITool.getName(DELETE_TRANSACTION_TITLE), JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					selected.getPreviousNode().remove(selected);
				}
			} else {
				int option = JOptionPane.showConfirmDialog(tree, GUITool.getName(DELETE_FOLDER_MSG), GUITool.getName(DELETE_FOLDER_TITLE), JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					removeNode(selected);
				}
			}

		}
		tree.updateUI();
	}

}
