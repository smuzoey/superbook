package superbook.dao;

import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import superbook.bean.OrderItem;
import superbook.bean.Product;
import superbook.util.DBUtil;


//已测试
public class OrderItemDao {
	/**
	 * 根据Oid 和 Uid 判断购物车数据库是否有
	 * @param oid
	 * @return
	 */
	public OrderItem searchByOid(int oid, int uid) {
		String sql = "select * from OrderItem where oid = ? and uid = ?;";
		OrderItem orderItem = new OrderItem();
		try {
			orderItem = DBUtil.select(sql, new BeanHandler<OrderItem>(OrderItem.class),oid, uid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderItem;
	}
	
	/**
	 * 添加购物车中的订单表
	 * @param o
	 */
	public void add(OrderItem o) {
		//判断此项是否已经存在数据库中,存在则不添加
		OrderItem orderItem = null;
		orderItem = searchByOid(o.getOid(),o.getUid());
		
		if(orderItem != null) {
			System.out.println("此OrderItem已经存在数据库");
		} 
		else {
			System.out.println("此OrderItem不存在数据库中");
		
			String sql = "insert into OrderItem(id,oid,uid,number) values(?,?,?,?);";
			try {
				DBUtil.update(sql, o.getId(),o.getOid(),o.getUid(),o.getNumber());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 *删除购物车订单 
	 * @param id
	 */
	public void delete(int uid, int oid) {
		String sql = "delete from OrderItem where uid=? and oid=?;";
		try {
			DBUtil.update(sql, uid, oid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据uid返回此人购物车的订单集
	 * @param uid
	 * @return
	 
	
	public List<OrderItem> selectByUid(int uid) {
		String sql = "select * from OrderItem where uid=?;";
		ResultSetHandler<List<OrderItem>> rsh = new BeanListHandler<OrderItem>(OrderItem.class);
		List<OrderItem> list = null;
		try {
			list = DBUtil.select(sql, rsh, uid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	
	public List<Product> selectByUid(int uid) {
		String sql = "select Product.pid,Product.cid,Product.isbn,Product.promotePrice,Product.createDate,Product.subTitle,Product.degree from Orders,OrderItem,Product where OrderItem.oid=Orders.oid and OrderItem.uid = ? and Product.pid=Orders.pid;";
		ResultSetHandler<List<Product>> rsh = new BeanListHandler<Product>(Product.class);
		List<Product> list = null;
		try {
			list = DBUtil.select(sql, rsh, uid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
