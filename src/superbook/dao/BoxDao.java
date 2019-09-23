package superbook.dao;

import superbook.bean.Box;
import superbook.util.DBUtil;

public class BoxDao {
	
	/**
	 * 添加盒子信息
	 * @param box
	 */
	public void add(Box box) {
		String sql = "insert into Box(bid, mid, boxState, size) values(?,?,?,?);";
		try {
			DBUtil.update(sql, box.getBid(), box.getMid(), box.getBoxState(), box.getSize());	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除柜子
	 * @param bid
	 */
	public void delete(int bid) {
		String sql = "delete from Box where bid = ?;";
		try {
			DBUtil.update(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据bid查询mid
	 * @param bid
	 * @return
	 */
	public String selectMid(int bid) {
		String sql = "select mid from Box where bid = ?;";
		try {
			DBUtil.select(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 根据bid查询boxState
	 * @param bid
	 * @return
	 */
	public String selectBoxState(int bid) {
		String sql = "select boxState from Box where bid = ?;";
		try {
			DBUtil.select(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 根据bid查询size大小
	 * @param bid
	 * @return
	 */
	public String selectSize(int bid) {
		String sql = "select size from Box where bid = ?;";
		try {
			DBUtil.select(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
