package priv.hcx.sender.msg.field.editor.impl.bean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FileConfigDao {

	List<FileConfigBean> queryByFieldId(@Param("fieldID") String fieldID);
	List<FileConfigBean> queryGroupByFieldId(@Param("fieldID") String fieldID);

	FileConfigBean queryById(@Param("id") String id);

	void update(FileConfigBean msg);

	void save(FileConfigBean msg);

	void delete(FileConfigBean msg);

}
