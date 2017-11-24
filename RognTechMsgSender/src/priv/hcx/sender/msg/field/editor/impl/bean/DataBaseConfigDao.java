package priv.hcx.sender.msg.field.editor.impl.bean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DataBaseConfigDao {
	List<DataBaseConfigBean> queryByFieldId(@Param("fieldID") String fieldID);

	List<DataBaseConfigBean> queryGroupByFieldId(@Param("fieldID") String fieldID);

	DataBaseConfigBean queryById(@Param("id") String id);

	void update(DataBaseConfigBean msg);

	void save(DataBaseConfigBean msg);

	void delete(DataBaseConfigBean msg);
}
