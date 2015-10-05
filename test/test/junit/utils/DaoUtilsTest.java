package test.junit.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.bdqn.utils.DaoUtils;

public class DaoUtilsTest {
	@Test
	public void testGetConnection() {
		System.out.println(DaoUtils.getConnection());
	}

	/**
	 * tb_trasaction表中插入10条记录
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testTransaction() throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn=DaoUtils.getConnection();
		 for(int i=1;i<=10;i++){
			 String sql = "insert into tb_trasaction values(?,?)";
			 Object[] parms = { i, "name"+i };
			 
			 //海啸发生
			 if(i==9){
				 int j=4/0;
			 }
			 
			 runner.update(conn, sql, parms);
		 }
		 
		 DaoUtils.trasactionCommit();
	}

}