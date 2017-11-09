package priv.hcx.sender.msg.header;

import java.util.List;

import javax.swing.JFrame;

public interface HeaderEditor {
	List<String> getConfigList();

	/**
	 * 通过请求头获取相应编辑窗口的名字
	 */
	public JFrame getEditorByName(String name);

	/**
	 * 获取Editor的名字
	 * 
	 * @return
	 */
	public String getEditorName();
}
