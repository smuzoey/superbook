package superbook.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import superbook.bean.Book;
import superbook.dao.BookDao;
import superbook.util.BookUtil;


@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 已测试
	 * 扫描书籍
	 * 所需参数: isbn
	 * @param request
	 * @param response
	 */
	public void scanBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		String isbn = (String)map.get("isbn");
		System.out.println(isbn);
		//根据isbn返回书籍详细信息
		Book book = new BookDao().selectByIsbn(isbn);
		if(book == null) {  //如果不在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中没有)");
			//调用ShowAPI找到书籍信息,并添加到数据库
			book = (new BookUtil()).getBook(isbn);
			System.out.println(book.toString());
			if(book.getIsbn() == null) {  //showApi 没有
				System.out.println("这本书不卖");
				JSONObject json = new JSONObject();
				json.put("flag", "false");
				write(response, json.toString());
				return;
			}
			else {
				(new BookDao()).add(book);
			}
		} else { //在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中存在)");
			System.out.println("ProductServlet.add " + book.toString());
		}
		JSONObject json = new JSONObject();
		json.put("book", book);
		write(response, json.toString());
	}
}
