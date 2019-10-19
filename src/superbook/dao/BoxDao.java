package superbook.dao;


import superbook.bean.Box;
import superbook.util.DBUtil;

public class BoxDao {
	
	/**
	 * 添加盒子信息
	 * @param box
	 */
	public void add(Box box) {
		String sql = "insert into box( mid, boxState, size) values(?,?,?);";
		try {
			DBUtil.update(sql,  box.getMid(), box.getBoxState(), box.getSize());	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除柜子
	 * @param bid
	 */
	public void delete(int bid) {
		String sql = "delete from box where bid = ?;";
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
	public int selectMid(int bid) {
		String sql = "select mid from box where bid = ?;";
		String result = null;
		try {
			result = DBUtil.select(sql, bid);
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 根据bid查询boxState
	 * @param bid
	 * @return
	 */
	public int selectBoxState(int bid) {
		String sql = "select boxState from box where bid = ?;";
		String result = null;
		try {
			result = DBUtil.select(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 根据bid查询size大小
	 * @param bid
	 * @return
	 */
	public int selectSize(int bid) {
		String sql = "select size from box where bid = ?;";
		String result = null;
		try {
			result = DBUtil.select(sql, bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 更改箱子状态
	 * @param bid 箱子号
	 * @param boxState  要更改的箱子状态
	 */
	public void changeState(int bid,int boxState) {
		String sql = "update box set  boxState = ? where bid = ?;";
		try {
			DBUtil.update(sql, boxState,bid);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 查询箱子状态
//	 * @param bid 箱子号
//	 * @return 返回箱子状态
//	 */
//	public int selectState(int bid) {
//		String sql = "select boxState from Box where bid = ?;";
//		String result = null;
//		try {
//			result = DBUtil.select(sql, bid);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return Integer.parseInt(result);
//	}
}
