package superbook.dao;

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
	
}
