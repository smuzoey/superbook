package superbook.dao;

import java.util.Date;

import org.apache.commons.dbutils.handlers.BeanHandler;

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
	
}
