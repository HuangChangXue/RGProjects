package priv.hcx.sender.msg.decoder.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.msg.decoder.impl.bean.Hex2StringConfigBean;

public interface Hex2StringConfigDao {
	Hex2StringConfigBean selectByName(@Param(value = "name") String name,@Param(value = "type") String type);

	void save(Hex2StringConfigBean bean);

	void update(Hex2StringConfigBean bean);

	List<Hex2StringConfigBean> selectAll(@Param(value = "type") String type);
}
