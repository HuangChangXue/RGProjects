package priv.hcx.sender.bean.res;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.bean.Folder;

public interface FolderDao {
	void update(Folder folder);

	void save(Folder folder);

	List<Folder> selectByParentId(@Param(value = "parentid") String parentid);
}
