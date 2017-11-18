package priv.hcx.sender.tool;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;

import priv.hcx.sender.bean.Folder;
import priv.hcx.sender.bean.Transaction;
import priv.hcx.sender.view.Const;
import priv.hcx.sender.view.actions.MenuActionListener;

public class GUITool implements Const {
	private static Properties prop = new Properties();
	static {
		InputStream is = null;
		try {
			is = "".getClass().getResourceAsStream("/META-INF/UI.properties");
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}

	}
	static Toolkit toolkit = Toolkit.getDefaultToolkit();

	public static int getScreenWidth() {
		return toolkit.getScreenSize().width;
	}

	public static int getScreenHeight() {
		return toolkit.getScreenSize().height;
	}

	public static int getProperWidthForDisplay() {
		return getScreenWidth() * 3 / 4;
	}

	public static int getProperHeightForDisplay() {
		return getScreenHeight() * 3 / 4;
	}

	public static Map<DefaultMutableTreeNode, Object> treeNodeMapping = new HashMap<DefaultMutableTreeNode, Object>();

	public static <T> DefaultMutableTreeNode createTreeNode(String name, T type) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(name) {

			@Override
			public boolean isLeaf() {

				Object bean = GUITool.treeNodeMapping.get(this);
				if (bean != null) {
					if (Folder.class.isAssignableFrom(bean.getClass())) {
						return false;
					} else if (Transaction.class.isAssignableFrom(bean.getClass())) {
						return true;
					}
				}
				return super.isLeaf();
			}

		};
		if (type != null) {
			treeNodeMapping.put(root, type);
		}
		return root;
	}

	public static <T> DefaultMutableTreeNode createTreeNode(T type) {

		String name = null;
		try {
			Method m = type.getClass().getDeclaredMethod("getName", new Class<?>[0]);
			name = (String) m.invoke(type, new Object[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createTreeNode(name, type);
	}

	public static void adjustFrame(JFrame frame) {
		adjustFrame(frame, true);
	}

	public static void adjustFrame(JFrame frame, boolean resize) {
		if (resize) {
			frame.setSize(getProperWidthForDisplay(), getProperHeightForDisplay());
			frame.setLocation(getScreenWidth() / 8, getScreenHeight() / 8);
		} else {
			frame.setLocation((getScreenWidth() - frame.getWidth()) / 2, (getScreenHeight() - frame.getHeight()) / 2);
		}
	}

	public static void adjustFrame(JDialog frame, boolean resize) {
		if (resize) {
			frame.setSize(getProperWidthForDisplay(), getProperHeightForDisplay());
			frame.setLocation(getScreenWidth() / 8, getScreenHeight() / 8);
		} else {
			frame.setLocation((getScreenWidth() - frame.getWidth()) / 2, (getScreenHeight() - frame.getHeight()) / 2);
		}
	}

	public static String getName(String comp) {
		if (prop.containsKey(comp)) {
			return prop.getProperty(comp);
		}
		return comp;
	}

	public static JMenuItem createMenuItem(String title) {
		JMenuItem menuItem = new JMenuItem(GUITool.getName(title));
		menuItem.setActionCommand(title);
		menuItem.addActionListener(MenuActionListener.getInst());
		return menuItem;
	}
}
