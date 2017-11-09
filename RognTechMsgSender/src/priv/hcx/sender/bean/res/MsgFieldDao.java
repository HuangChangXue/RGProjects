package priv.hcx.sender.bean.res;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.hcx.sender.bean.MsgField;

public interface MsgFieldDao {
	List<MsgField> queryByTransactonId(@Param("transactionid") String transactionid);
	MsgField queryById(@Param("id") String id);
	void update(MsgField msg);
	void save(MsgField msg);
}
