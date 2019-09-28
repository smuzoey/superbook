package superbook.dao;

import superbook.bean.Book;
import superbook.util.DBUtil;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import java.util.List;

public class BookDao {
	
	/**
	 * 添加书籍信息
	 * @param b  //Book
	 */
	public void add(Book b) {
		String sql = "insert into Book(isbn,publisher,binding,page,author,paper,title,price,img,gist) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			DBUtil.update(sql,b.getIsbn(),b.getPublisher(),b.getBinding(),b.getPage(),b.getAuthor(),b.getPaper(),b.getTitle(),b.getPrice(),b.getImg(),b.getGist());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除书籍
	 * @param isbn
	 */
	public void delete(String isbn) {
		String sql = "delete from Book where isbn = ?";
		try {
			DBUtil.update(sql, isbn);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  根据书名返回书籍信息
	 * @param title
	 * @return
	 */
	public List<Book> selectByTitle(String title) {
		String sql = "select * from Book where title = ?";
		ResultSetHandler<List<Book>> rsh = new BeanListHandler<Book>(Book.class);
		List<Book> list = null;
		try {
			list = DBUtil.select(sql,rsh,title);
			for(Book b : list) {
				System.out.println(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据isbn返回书籍信息
	 * @param isbn
	 * @return
	 */
	public Book selectByIsbn(String isbn) {
		String sql = "select * from Book where isbn = ?";
		Book book = new Book();
		try {
			book = DBUtil.select(sql,new BeanHandler<Book>(Book.class),isbn);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
}
