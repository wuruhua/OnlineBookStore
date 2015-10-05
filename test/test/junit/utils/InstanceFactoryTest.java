package test.junit.utils;

import org.junit.Test;

import com.bdqn.biz.OrderBiz;
import com.bdqn.utils.InstanceFactory;


public class InstanceFactoryTest {
	
	@Test
	public void testCreateInstance() {
		OrderBiz order=InstanceFactory.getInstance().createInstance(OrderBiz.class);
		System.out.println(order);
	}
}
