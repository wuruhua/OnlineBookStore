package test.junit.biz;

import org.junit.Test;

import com.bdqn.biz.UserBiz;
import com.bdqn.domain.User;
import com.bdqn.utils.DaoUtils;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.UserBean;

public class UserBizImplTest {
	private UserBiz biz = InstanceFactory.getInstance().createInstance(
			UserBiz.class);
	@Test
	public void testSearchUserByName() {
		UserBean user=new UserBean();
		user.setName("夏诗涵");
		boolean isExists=biz.searchUserByName(user);
		System.out.println(isExists);
	}
	
	@Test
	public void testUserLogin() {
		UserBean user=new UserBean();
		user.setName("夏诗涵");
		user.setPassword("xsh");
		User u= biz.userLogin(user);
		System.out.println(u);
	}
	@Test
	public void testUserRegist() {
		
		UserBean user=new UserBean();
		user.setEmail("xsh@sina.com.cn");
		user.setName("夏诗涵");
		user.setPassword("xsh");
		user.setRepassword("xsh");
		
		biz.userRegist(user);
		commonDealwith();
	}
	
	private void commonDealwith() {
		DaoUtils.trasactionCommit();
		DaoUtils.releaseConn();
	}

}
