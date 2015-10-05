package test.junit.dao;

import java.util.UUID;

import org.junit.Test;

import com.bdqn.dao.UserDao;
import com.bdqn.domain.User;
import com.bdqn.utils.BizUtils;
import com.bdqn.utils.DaoUtils;
import com.bdqn.utils.InstanceFactory;

public class UserDaoImplTest {
	private UserDao dao=InstanceFactory.getInstance().createInstance(UserDao.class);
	@Test
	public void testSaveUser() {
		User user = new User(UUID.randomUUID().toString(),"胡鑫鑫",BizUtils.getMd5EncodePwd("hxx"),"hxx@sina.com.cn");
		dao.saveUser(user);
		commonDealwith();
	}

	private void commonDealwith() {
		DaoUtils.trasactionCommit();
		DaoUtils.releaseConn();
	}
	
	@Test
	public void testFindUserByName() {
		System.out.println(dao.findUserByName("胡鑫鑫"));
		commonDealwith();
	}

	@Test
	public void testFindUser() {
		System.out.println(dao.findUser("胡鑫鑫",BizUtils.getMd5EncodePwd("hxx")));
		commonDealwith();
	}

}
