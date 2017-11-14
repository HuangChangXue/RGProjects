package priv.hcx.sender.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.ibatis.session.SqlSession;

import priv.hcx.sender.bean.Folder;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.bean.res.FolderDao;
import priv.hcx.sender.bean.res.TransactionDao;
import priv.hcx.sender.db.ui.DataBaseConnectionEditorUI;
import priv.hcx.sender.server.ui.ServerConfUI;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.Const;
import priv.hcx.sender.view.SenderMainFrame;

public class MenuActionListener implements ActionListener, Const {
	static private MenuActionListener inst = new MenuActionListener();

	private MenuActionListener() {
	}

	public static MenuActionListener getInst() {
		return inst;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if (cmd.equals(MAIN_WINDOW_MENU_CONFIG_DATABASE_NEW)) {
			JDialog frame = new DataBaseConnectionEditorUI(SenderMainFrame.getMainFrame(), true);
			GUITool.adjustFrame(frame, false);
			frame.setVisible(true);
		} else if (cmd.equals(MAIN_WINDOW_MENU_CONFIG_SERVER_ADD)) {
			JDialog frame = new ServerConfUI(SenderMainFrame.getMainFrame());
			GUITool.adjustFrame(frame, false);
			frame.setVisible(true);
		} else if (cmd.equals(MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION_TYPE) || cmd.equalsIgnoreCase(MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION)) {
			JTree tree = SenderMainFrame.getNaviTree();
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
			if (cmd.equals(MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION_TYPE)) {
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
				Transaction tran = new Transaction(tranName);
				if (selected.isLeaf()) {
					selected = (DefaultMutableTreeNode) selected.getParent();
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
			tree.updateUI();
		} else {
			List<UnRecognizedCmdListener> services = CommonTools.loadService(UnRecognizedCmdListener.class);
			for (UnRecognizedCmdListener listener : services) {
				if (listener.isRecognized(cmd)) {
					listener.doAction(cmd);
				}
			}
		}
	}
}
