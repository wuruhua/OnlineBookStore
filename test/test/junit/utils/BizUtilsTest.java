package test.junit.utils;

import org.junit.Test;

import com.bdqn.utils.BizUtils;

import junit.framework.TestCase;

public class BizUtilsTest extends TestCase {

	@Test
	public void testGetMd5EncodePwd() {
		System.out.println(BizUtils.getMd5EncodePwd("admin"));
	}
}
