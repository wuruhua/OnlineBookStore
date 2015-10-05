package test.junit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.bdqn.dao.BookDao;
import com.bdqn.domain.Book;
import com.bdqn.domain.QueryResult;
import com.bdqn.utils.DaoUtils;
import com.bdqn.utils.InstanceFactory;

public class BookDaoImplTest {
	private BookDao dao = InstanceFactory.getInstance().createInstance(
			BookDao.class);

	@Test
	public void testSaveBook() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(UUID.randomUUID().toString(), "泰戈尔诗集", 18.00,
				"images/book/book_01.gif", 979));
		books.add(new Book(UUID.randomUUID().toString(), "痕记", 22.80,
				"images/book/book_02.gif", 977));
		books.add(new Book(UUID.randomUUID().toString(), "天堂之旅", 25.00,
				"images/book/book_03.gif", 989));
		books.add(new Book(UUID.randomUUID().toString(), "钱钟书集", 332.50,
				"images/book/book_04.gif", 999));
		books.add(new Book(UUID.randomUUID().toString(), "赵俪生高昭—夫妻回忆录", 38.00,
				"images/book/book_05.gif", 998));
		books.add(new Book(UUID.randomUUID().toString(), "无聊斋", 28.00,
				"images/book/book_06.gif", 998));
		books.add(new Book(UUID.randomUUID().toString(), "一颗热土豆是一张温馨的床", 38.00,
				"images/book/book_07.gif", 999));
		books.add(new Book(UUID.randomUUID().toString(), "李戡戡乱记", 22.00,
				"images/book/book_08.gif", 999));
		books.add(new Book(UUID.randomUUID().toString(), "生生世世未了缘", 17.50,
				"images/book/book_09.gif", 999));
		books.add(new Book(UUID.randomUUID().toString(), "一生有多少爱", 17.50,
				"images/book/book_10.gif", 999));

		for(int i=1;i<=10;i++){
			for (Book book : books) {
				book.setId(UUID.randomUUID().toString());
				book.setName(book.getName()+i);
				dao.saveBook(book);
			}
		}
		commonDealwith();
	}

	private void commonDealwith() {
		DaoUtils.trasactionCommit();
		DaoUtils.releaseConn();
	}

	@Test
	public void testFindAllBooks() {
		int startIndex=1;
		int endIndex=3;
//		String name="";
		String name="钱";
		QueryResult<Book> bookResults=dao.findAllBooks(name,startIndex, endIndex);
		System.out.println(bookResults);
	}
	
	@Test
	public void testFindBooksByIds() {
		String []ids={"8fecd962-2227-4d26-a3a7-954774f0000c","f7ad3476-eb26-4a11-a9c5-1ed2c86292fa"};
		System.out.println(dao.findBooksByIds(ids));
		
		
	}
}
