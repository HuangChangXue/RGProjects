package priv.hcx.sender.msg.field.editor.impl.bean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.bean.MsgField;

public interface ConstConfigDao {

	List<ConstConfigBean> queryByFieldId(@Param("fieldID") String fieldID);

	ConstConfigBean queryById(@Param("id") String id);

	void update(ConstConfigBean msg);

	void saveMsg(ConstConfigBean msg);

	void delete(ConstConfigBean msg);

}
