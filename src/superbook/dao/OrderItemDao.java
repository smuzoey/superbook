package superbook.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import superbook.bean.OrderItem;
import superbook.util.DBUtil;


//已测试
public class OrderItemDao {

	/**
	 * 添加购物车中的订单表
	 * @param o
	 */
	public void add(OrderItem o) {
		String sql = "insert into OrderItem(id,oid,uid,number) values(?,?,?,?);";
		try {
			DBUtil.update(sql, o.getId(),o.getOid(),o.getUid(),o.getNumber());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *删除购物车订单 
	 * @param id
	 */
	public void delete(int id) {
		String sql = "delete from OrderItem where id = ?;";
		try {
			DBUtil.update(sql, id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id返回订单信息
	 * @param id
	 * @return
	 */
	public OrderItem selectByid(int id) {
		OrderItem o = new OrderItem();
		String sql = "select * from OrderItem where id = ?;";
		try {
			o = DBUtil.select(sql, new BeanHandler<OrderItem>(OrderItem.class), id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(o);
		return o;
	}
	

}
