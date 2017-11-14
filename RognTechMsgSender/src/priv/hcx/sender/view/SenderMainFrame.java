package priv.hcx.sender.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.ibatis.session.SqlSession;

import priv.hcx.sender.bean.Folder;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.bean.res.FolderDao;
import priv.hcx.sender.bean.res.TransactionDao;
import priv.hcx.sender.db.DBConf;
import priv.hcx.sender.db.DataBase;
import priv.hcx.sender.msg.encoder.MsgEncoder;
import priv.hcx.sender.msg.header.HeaderEditor;
import priv.hcx.sender.server.Server;
import priv.hcx.sender.server.ServerConf;
import priv.hcx.sender.tool.CommonTools;
import priv.hcx.sender.tool.GUITool;
import priv.hcx.sender.view.tree.JTreePopupMenu;
import priv.hcx.sender.view.tree.NodeRenderer;
import priv.hcx.sender.view.tree.TreeNodeListerner;

public class SenderMainFrame extends JFrame implements Const {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SenderMainFrame frame = null;

	public static SenderMainFrame getMainFrame() {
		return frame;
	}

	public SenderMainFrame() {
		frame = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUITool.adjustFrame(this);
		this.setLayout(new BorderLayout());
		this.initMenu();
		this.initMainPanel();
		this.setVisible(true);
		this.setTitle(GUITool.getName(MAIN_WINDOW_TITLE));
	}

	static DefaultMutableTreeNode treeRoot = null;
	static DefaultTreeModel treeModel = null;

	private void initTreeNodes(DefaultMutableTreeNode root) {
		Object bean = GUITool.treeNodeMapping.get(root);
		List<Folder> folder = null;
		if (bean != null) {
			if (Folder.class.isAssignableFrom(bean.getClass())) {
				SqlSession session = CommonTools.getSQLSession(true);
				FolderDao dao = CommonTools.getMapper(session, FolderDao.class);
				folder = dao.selectByParentId(((Folder) bean).getId());
				CommonTools.closeSession(session);
			}
		} else {
			SqlSession session = CommonTools.getSQLSession(true);
			FolderDao dao = CommonTools.getMapper(session, FolderDao.class);
			folder = dao.selectByParentId("root");
			CommonTools.closeSession(session);
		}
		for (Folder f : folder) {
			DefaultMutableTreeNode child = GUITool.createTreeNode(f.getName(), f);
			root.add(child);
			initTreeNodes(child);
			initFolderTrans(f, child);
		}

	}

	private void initFolderTrans(Folder f, DefaultMutableTreeNode node) {
		SqlSession session = CommonTools.getSQLSession(true);
		TransactionDao dao = CommonTools.getMapper(session, TransactionDao.class);
		List<Transaction> trans = dao.selectByfolderid(f.getId());
		for (Transaction tran : trans) {
			node.add(GUITool.createTreeNode(tran));
		}

		CommonTools.closeSession(session);
	}
	private  static JTree tree=null;
	public static JTree getNaviTree(){
		return tree;
	}
	private void initMainPanel() {

		DefaultMutableTreeNode root = GUITool.createTreeNode(new Folder("交易类型"));

		treeRoot = root;
		initTreeNodes(root);
		DefaultTreeModel treeModel = new DefaultTreeModel(root) {
			@Override
			public boolean isLeaf(Object node) {

				if (node instanceof DefaultMutableTreeNode) {
					Object bean = GUITool.treeNodeMapping.get(node);
					if (bean != null) {
						if (Folder.class.isAssignableFrom(bean.getClass())) {
							return false;
						} else if (Transaction.class.isAssignableFrom(bean.getClass())) {
							return true;
						}
					}
				}
				return super.isLeaf(node);
			}
		};
		treeModel.addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				System.out.println(e);

			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				// TODO Auto-generated method stub

			}
		});
		SenderMainFrame.treeModel = treeModel;
		 tree= new JTree(treeModel);
		// tree.setRootVisible(false);
		new JTreePopupMenu(tree);
		JScrollPane jsp = new JScrollPane(tree);
		tree.setBackground(jsp.getBackground());
		tree.setEditable(true);
		tree.addMouseListener(new TreeNodeListerner(tree));
		tree.setCellRenderer(new NodeRenderer(jsp.getBackground()));

		jsp.setBorder(new TitledBorder(GUITool.getName(MAIN_WINDOW_CONTENT_TREE_BORDER)));

		JSplitPane mansplit = new JSplitPane();
		mansplit.setLeftComponent(jsp);

		// .add(CommonTools.loadService(priv.hcx.sender.msg.field.editor.FieldEditor.class).iterator().next().getEditPaneByFieldId(null));
		split.setLeftComponent(null);
		split.setRightComponent(null);
		split.setBorder(null);
		mansplit.setRightComponent(split);
		mansplit.setBorder(null);
		fieldEditorPanel.setBorder(new TitledBorder(GUITool.getName(MAIN_WINDOW_MSG_FIELD_SETTING_BORDER)));

		this.add(mansplit, BorderLayout.CENTER);
	}

	public void setMessageEditor(Component comp) {
		split.setLeftComponent(comp);
	}

	private JSplitPane split = new JSplitPane();
	JPanel fieldEditorPanel = new JPanel();

	public void setFieldEdirot(Component comp) {
		fieldEditorPanel.setLayout(new BorderLayout());
		fieldEditorPanel.removeAll();
		fieldEditorPanel.add(comp, BorderLayout.CENTER);
		split.setRightComponent(fieldEditorPanel);
	}

	private void initMenu() {
		JMenuBar menubar = new JMenuBar();
		menubar.add(createFileMenu());
		menubar.add(createToolMenu());

		this.setJMenuBar(menubar);
	}

	private JMenu createServerMenu() {
		JMenu menu = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_SERVER));
		menu.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_CONFIG_SERVER_ADD));
//		JMenu edit=new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_SERVER_EDIT));
//		menu.add(edit);
		menu.add(new JSeparator());
		List<ServerConf> servers=Server.getAllServerConf();
		for(ServerConf s:servers){
//			edit.add(GUITool.createMenuItem(s.getName()));
			menu.add(GUITool.createMenuItem(s.getName()));
		}
		return menu;
	}

	
	private JMenu createToolMenu() {
		JMenu menu = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG));
		menu.add(createServerMenu());
		menu.addSeparator();
		JMenu msg = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_MSG));
		JMenu encoder = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_MSG_ENCODER));
		JMenu head = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_MSG_HEAD));
		List<HeaderEditor> headEditors = CommonTools.loadService(HeaderEditor.class);
		for (HeaderEditor editor : headEditors) {
			head.add(GUITool.createMenuItem(editor.getEditorName()));
		}
		msg.add(head);
		List<MsgEncoder> encoders = CommonTools.loadService(MsgEncoder.class);
		for (MsgEncoder coder : encoders) {
			encoder.add(GUITool.createMenuItem(coder.getEncoderName()));
		}
		msg.add(encoder);
		menu.add(msg);
		
		menu.add(createDatatbaseMenu());
		return menu;
	}
private JMenu createDatatbaseMenu(){
	JMenu database = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_CONFIG_DATABASE));
	database.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_CONFIG_DATABASE_NEW));
	List<DBConf> dbconfs=DataBase.getAllDbConf();
	for(DBConf conf:dbconfs){
		database.add(GUITool.createMenuItem(conf.getName()));
	}

	return database;
}
	private JMenu createFileMenu() {
		JMenu menu = new JMenu(GUITool.getName(MAIN_WINDOW_MENU_FILE));
		menu.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION_TYPE));
		menu.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_FILE_CREATE_NEW_PROJECT));
		menu.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION));
		menu.add(GUITool.createMenuItem(MAIN_WINDOW_MENU_FILE_SAVE_TRANSACTION));
		return menu;
	}

}

class TabelCellEditor extends DefaultCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3122278057467274444L;

	public TabelCellEditor() {
		super(new JTextField());
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
