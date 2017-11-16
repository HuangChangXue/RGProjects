package priv.hcx.sender.msg.header;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import priv.hcx.sender.bean.MsgField;
import priv.hcx.sender.bean.msg.MsgHead;

public interface HeaderEditor {
	List<String> getConfigList();

	/**
	 * 通过请求头获取相应编辑窗口的名字
	 */
	public JDialog getEditorFrameByName(String name);

	/**
	 * 获取Editor的名字
	 * 
	 * @return
	 */
	public String getEditorName();

	/**
	 * 根据指定的配置文件及 字段生成请求头
	 * @param fields
	 * @return
	 */
	public MsgHead createHead(String name,List<MsgField> fields);
	/**
	 * 保存所有的配置文件
	 */
	public void saveConfig();
	/**
	 * 根据配置名字获取配置的ID
	 * @param name
	 * @return
	 */
	public String getConfigIdByName(String name);
	/**
	 * 根据配置的ID获取对应的名字
	 * @param id
	 * @return
	 */
	public String getConfigNameByID(String id);
}
