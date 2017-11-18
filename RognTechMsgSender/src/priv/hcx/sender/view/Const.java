package priv.hcx.sender.view;

public interface Const {
	public static String MAIN_WINDOW_TITLE = "windowTitle";
	// 文件菜单
	public static String MAIN_WINDOW_MENU_FILE = "menufile";
	public static String MAIN_WINDOW_MENU_FILE_CREATE_NEW_PROJECT = "menufilenewProject";
	public static String MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION_TYPE = "menufilenewTransactiontype";
	public static String MAIN_WINDOW_MENU_FILE_CREATE_NEW_TRANSACTION = "menufilenewTransaction";
	public static String MAIN_WINDOW_MENU_FILE_SAVE_TRANSACTION = "menufilesaveTransaction";

	// 工具菜单
	public static String MAIN_WINDOW_MENU_CONFIG = "menuconf";
	public static String MAIN_WINDOW_MENU_CONFIG_SERVER = "menuconfserver";
	public static String MAIN_WINDOW_MENU_CONFIG_SERVER_ADD = "newServer";
	public static String MAIN_WINDOW_MENU_CONFIG_SERVER_EDIT = "editServer";
	public static String MAIN_WINDOW_MENU_CONFIG_MSG = "menuconfmsg";
	public static String MAIN_WINDOW_MENU_CONFIG_MSG_HEAD = "menuconfmsghead";
	public static String MAIN_WINDOW_MENU_CONFIG_MSG_ENCODER = "menuconfmsgencoder";
	public static String MAIN_WINDOW_MENU_CONFIG_DATABASE = "database";
	public static String MAIN_WINDOW_MENU_CONFIG_DATABASE_NEW = "newDataBase";
	// 工作区
	public static String MAIN_WINDOW_CONTENT_TREE_BORDER = "transList";

	// 消息编辑器

	public static String MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE = "headertype";
	public static String MAIN_WINDOW_MSG_EDITOR_HEAD_PANEL_HEADER_TYPE_CONFIG = "headertypefile";
	public static String MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_NEW = "addrecord";
	public static String MAIN_WINDOW_MSG_EDITOT_TABLE_POPUP_DEL = "delrecord";

	public static String MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TRANSACTION = "newTransaction";
	public static String MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_DEL = "delrecord";
	public static String MAIN_WINDOW_MSG_EDITOT_TREE_POPUP_NEW_TYPE = "newFolder";
	public static String MAIN_WINDOW_MSG_FIELD_SETTING_BORDER = "fieldSetting";
	// 数据类型：
	public static String TYPE_STRING = "string";
	public static String TYPE_BOOLEAN = "boolean";
	public static String TYPE_INT = "integer";
	public static String[] TYPES = new String[] { TYPE_BOOLEAN, TYPE_INT, TYPE_STRING };

	public static String INPUTBOX_NEW_FOLER_TITLE = "inputFolderNameTitle";
	public static String INPUTBOX_NEW_FOLER_MSG = "inputFolderName";

	public static String INPUTBOX_NEW_TRANSACTION_TITLE = "inputTransactionNameTitle";
	public static String INPUTBOX_NEW_TRANSACTION_MSG = "inputTransactionName";

	public static String DELETE_FOLDER_TITLE = "delFolderTitle";
	public static String DELETE_FOLDER_MSG = "delFolder";
	public static String DELETE_TRANSACTION_TITLE = "delTransactionTitle";
	public static String DELETE_TRANSACTION_MSG = "delTransaction";
}
