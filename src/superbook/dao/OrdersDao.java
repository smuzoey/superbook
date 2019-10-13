package superbook.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
	
	/**
	 * 新添加
	 * 修改订单的bid
	 * @param oid
	 * @param bid
	 */
	public void changeBid(int oid, int bid) {
		String sql = "update Orders set bid=? where oid=?;";
		try {
			DBUtil.update(sql, bid, oid);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新添加
	 * 根据pid返回订单状态
	 * @param pid
	 */
	public int getOrderStateByPid(int pid) {
		String sql = "select orderState from Orders where pid = ?;";
		int orderState = 0;
		try {
			orderState = Integer.parseInt(DBUtil.select(sql, pid));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderState;
	}
	
	/**
	 * 新添加
	 * 根据pid返回订单全部信息
	 * @param pid
	 * @return
	 */
	public Orders selectByPid(int pid) {
		String sql = "select * from Orders where pid=?;";
		Orders order = new Orders();
		try {
			order = DBUtil.select(sql, new BeanHandler<Orders>(Orders.class), pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	
	/**
	 * 新添加
	 * 更新此订单的全部信息
	 * @param o
	 */
	public void updateOrder(Orders o) {
		String sql = "update Orders set bid=?,orderCode=?,receiver=?,phone=?,userMessage=?,createTime=?,payDate=?,deliverDate=?,confirmDate=?,orderState=? where pid=?;";
		try {
			DBUtil.update(sql, o.getBid(),o.getOrderCode(),o.getReceiver(),o.getPhone(),o.getUserMessage(),o.getCreateTime(),o.getPayDate(),o.getDeliverDate(),o.getConfirmDate(),o.getOrderState(),o.getPid());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据uid返回他卖的全部订单
	 * @param uid
	 * @return
	 */
	public List<Orders> selectByUid(int uid) {
		String sql = "select * from Orders where uid = ?";
		ResultSetHandler<List<Orders>> rsh = new BeanListHandler<Orders>(Orders.class);
		List<Orders> list = null;
		try {
			list = DBUtil.select(sql,rsh,uid);
			for(Orders o : list) {
				System.out.println(o);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * 根据receiver返回他买的全部订单
	 * @param receiver
	 * @return
	 */
	public List<Orders> selectByReceiver(int receiver) {
		String sql = "select * from Orders where receiver= ?";
		ResultSetHandler<List<Orders>> rsh = new BeanListHandler<Orders>(Orders.class);
		List<Orders> list = null;
		try {
			list = DBUtil.select(sql,rsh,receiver);
			for(Orders o : list) {
				System.out.println(o.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
