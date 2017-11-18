package priv.hcx.sender.bean.res;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.bean.Folder;
import priv.hcx.sender.bean.Transaction;

public interface TransactionDao {
	void update(Transaction tran);
	void save(Transaction tran);
	List<Transaction> selectByfolderid(@Param(value="folderid") String folderid);

}
