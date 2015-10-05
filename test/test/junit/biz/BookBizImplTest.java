package test.junit.biz;

import org.junit.Test;

import com.bdqn.biz.BookBiz;
import com.bdqn.domain.Book;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

public class BookBizImplTest {
	private BookBiz biz = InstanceFactory.getInstance().createInstance(
			BookBiz.class);
	@Test
	public void testListAllBooks() {
		QueryInfo info = new QueryInfo();
		info.setCondition("泰戈尔");
		info.setCurrentPage(2);
		PageBean<Book> bookResults=biz.listAllBooks(info);
		System.out.println(bookResults);
		
	}

}
