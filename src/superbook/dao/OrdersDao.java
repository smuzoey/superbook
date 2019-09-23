package superbook.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import superbook.bean.Orders;
import superbook.util.DBUtil;
import superbook.util.DateUtil;


//已测试

public class OrdersDao {
	/**
	 * 添加订单
	 * @param order
	 */
	public void add(Orders order) {
		String sql = "insert into Orders(oid,uid,bid,orderCode,receiver,phone,userMessage,createTime,payDate,deliverDate,confirmDate,orderState,pid) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			DBUtil.update(sql, order.getOid(),order.getUid(),order.getBid(),order.getOrderCode(),order.getReceiver(),order.getPhone(),order.getUserMessage(), DateUtil.dtot(order.getCreateTime()),DateUtil.dtot(order.getPayDate()),DateUtil.dtot(order.getDeliverDate()),DateUtil.dtot(order.getConfirmDate()), order.getOrderState(),order.getPid());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据oid删除订单
	 * @param oid
	 */
	public void delete(int oid) {
		String sql = "delete from Orders where oid = ?;";
		try {
			DBUtil.update(sql, oid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据oid返回订单全部信息
	 * @param oid
	 * @return
	 */
	public Orders selectByOid(int oid) {
		String sql = "select * from Orders where oid = ?;";
		Orders order = new Orders();
		try {
			order = DBUtil.select(sql,new BeanHandler<Orders>(Orders.class), oid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(order);
		return order;
	}
	
}
