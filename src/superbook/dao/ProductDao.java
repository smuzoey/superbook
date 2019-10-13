package superbook.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import superbook.bean.Product;
import superbook.util.DBUtil;
import superbook.util.DateUtil;


//已测试

public class ProductDao {
	
	/**
	 * 添加Product
	 * @param p
	 */
	public void add(Product p) {
		String sql = "insert into Product(pid,cid,isbn,promotePrice,createDate,subTitle,degree) values(?,?,?,?,?,?,?);";
		try {
			DBUtil.update(sql, p.getPid(),p.getCid(),p.getIsbn(),p.getPromotePrice(),DateUtil.dtot(p.getCreateDate()),p.getSubTitle(),p.getDegree());
			System.out.println("Product().Dao成功插入");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据pid删除产品信息
	 * @param pid
	 */
	public void delete(int pid) {
		String sql = "delete from Product where pid = ?;";
		try {
			DBUtil.update(sql, pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据pid返回产品全部信息
	 * @param pid
	 * @return
	 */
	public Product selectByPid(int pid) {
		String sql = "select * from Product where pid=?;";
		Product p = new Product();
		try {
			p = DBUtil.select(sql, new BeanHandler<Product>(Product.class), pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(p);
		return p;
	}
	
	/**
	 * 根据ISBN返回产品
	 * @param isbn
	 * @return
	 */
	
	public Product selectByISBN(String isbn) {
		String sql = "select * from Product where isbn=?;";
		Product p = new Product();
		try {
			p = DBUtil.select(sql, new BeanHandler<Product>(Product.class), isbn);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(p);
		return p;
	}
	
	/**
	 * 根据ISBN返回产品列表
	 * @param isbn
	 * @return
	 */
	public List<Product> selectListByISBN(String isbn) {
		String sql = "select * from Product where isbn = ?";
		ResultSetHandler<List<Product>> rsh = new BeanListHandler<Product>(Product.class);
		List<Product> list = null;
		try {
			list = DBUtil.select(sql,rsh,isbn);
			for(Product p : list) {
				System.out.println(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 修改新旧程度
	 * @param pid
	 * @param degree
	 */
	public void changedegree(int pid , int degree) {
		String sql = "update Product set  degree =? where pid = ?;";
		try {
			DBUtil.update(sql, degree,pid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改价格
	 * @param pid
	 * @param promotePrice
	 */
	public void changepromotePrice(int pid , int promotePrice) {
		String sql = "update Product set  promotePrice =? where pid = ?;";
		try {
			DBUtil.update(sql, promotePrice,pid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改发布日期
	 * @param pid
	 * @param createDate
	 */
	public void changecreateDate(int pid , Date createDate) {
		String sql = "update Product set  createDate =? where pid = ?;";
		try {
			DBUtil.update(sql, DateUtil.dtot(createDate),pid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新添加
	 * 返回最新自增的Pid 
	 * @return
	 */
	public int getPid() {
		String sql = "select last_insert_id();";
		try {
			return Integer.parseInt(DBUtil.select(sql));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 新添加
	 * 根据类别和订单状态返回产品信息
	 * @param cid
	 * @return
	 */
	public List<Product> selectByCid(int cid) {
		String sql = "select Product.pid,cid,isbn,promotePrice,createDate,subTitle,degree from Product,Orders where Product.pid=Orders.pid and OrderState=? and cid=?;";
		ResultSetHandler<List<Product>> rsh = new BeanListHandler<Product>(Product.class);
		int orderState = 2;
		List<Product> list = null;
		try {
			list = DBUtil.select(sql, rsh, orderState, cid);
			for(Product p:list) {
				System.out.println(p.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
