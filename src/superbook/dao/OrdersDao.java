package superbook.dao;

import java.util.Date;

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
	/**
	 * 更改订单买家信息
	 * @param receiver 买家
	 * @param userMessage  买家信息
	 * @param phone 买家电话
	 */
	public void changeUser(int oid,int receiver, String userMessage,String phone) {
		String sql = "update Orders set receiver =? ,userMessage= ?, phone=? where oid = ?;";
		try {
			DBUtil.update(sql, receiver,userMessage,phone,oid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更改支付时间
	 * @param oid
	 * @param payDate
	 */
	public void changePayDate(int oid,Date payDate) {
		String sql = "update Orders set payDate =? where oid = ?;";
		try {
			DBUtil.update(sql, DateUtil.dtot(payDate),oid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更改发货时间
	 * @param oid
	 * @param deliverDate
	 */
	public void changedeliverDate(int oid,Date deliverDate) {
		String sql = "update Orders set  deliverDate =? where oid = ?;";
		try {
			DBUtil.update(sql, DateUtil.dtot( deliverDate),oid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	/**
	 * 更改确认收货日期
	 * @param oid
	 * @param confirmDate
	 */
	public void changeconfirmDate(int oid,Date confirmDate) {
		String sql = "update Orders set  confirmDate =? where oid = ?;";
		try {
			DBUtil.update(sql, DateUtil.dtot( confirmDate),oid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更改订单状态
	 * @param oid
	 * @param orderState
	 */
	public void changeState(int oid , int orderState) {
		String sql = "update Orders set  orderState =? where oid = ?;";
		try {
			DBUtil.update(sql, orderState,oid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
}
