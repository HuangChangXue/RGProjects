package priv.hcx.sender.msg.field.editor.impl.bean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.bean.MsgField;

public interface RandomConfigDao {

	List<MsgField> queryByFieldId(@Param("fieldID") String fieldID);

	RandomConfigBean queryById(@Param("id") String id);

	void update(RandomConfigBean msg);

	void save(RandomConfigBean msg);

	void delete(RandomConfigBean msg);

}
