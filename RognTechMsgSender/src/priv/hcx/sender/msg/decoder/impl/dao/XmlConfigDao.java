package priv.hcx.sender.msg.decoder.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.msg.decoder.impl.bean.XmlConfigBean;

public interface XmlConfigDao {
	XmlConfigBean selectByName(@Param(value = "name") String name);

	void save(XmlConfigBean bean);

	void update(XmlConfigBean bean);

	List<XmlConfigBean> selectAll(@Param(value = "type") String type);
}
