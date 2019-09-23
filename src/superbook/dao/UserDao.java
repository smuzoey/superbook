package superbook.dao;


import org.apache.commons.dbutils.handlers.BeanHandler;
import superbook.bean.User;
import superbook.util.DBUtil;

public class UserDao {
	/**
	 * 添加用户
	 * @param openId // 只存不取
	 * @param uid    //openId映射的
	 * @param nickName
	 */
	public void add(int  openId, int uid, String nickName) {
		String sql = "insert into `user` (openId,uid,nickName) values(?,?,?)";
		try {
			DBUtil.update(sql,openId,uid,nickName);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	

	
	/**
	 * 根据uid删除信息
	 * @param uid
	 */
	public void delete(int uid) {
		String sql = "delete from  `user`  where uid = ?";
		try {
			DBUtil.update(sql, uid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据uid查询openid
	 * @param uid
	 * @return
	 */
	public String selectOpenid(int uid) {
		String sql = "select openId from user where uid = ?";
		try {
			System.out.println(DBUtil.select(sql, uid));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok"; 
	}
	
	/**
	 * 根据uid查询openid
	 * @param uid
	 * @return
	 */
	public String selectnickName(int uid) {
		String sql = "select nickName from user where uid = ?";
		try {
			System.out.println(DBUtil.select(sql, uid));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok"; 
	}
}
