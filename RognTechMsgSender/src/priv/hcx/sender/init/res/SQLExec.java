package priv.hcx.sender.init.res;

import org.apache.ibatis.annotations.Param;

public interface SQLExec {

	void exec(@Param("sql") String sql);

}
