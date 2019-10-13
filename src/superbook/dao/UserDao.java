package superbook.dao;


import org.apache.commons.dbutils.handlers.BeanHandler;

import superbook.bean.User;
import superbook.util.DBUtil;

public class UserDao {
	/**
	 * 添加用户
	 * @param openId // 只存不取
	 * @param nickName
	 */
	public void add(String  openId, String nickName, String uuid) {
		String sql = "insert into `user` (openId,nickName,uuid) values(?,?,?)";
		try {
			DBUtil.update(sql,openId,nickName,uuid);
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
		String result = null;
		try {
			result = DBUtil.select(sql, uid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	/**
	 * 根据openid查询uid
	 * @param uid
	 * @return
	 */
	public String selectuid(String openid) {
		String sql = "select uid from user where openId = ?";
		String result = null;
		try {
			result = DBUtil.select(sql, openid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	
	/**
	 * 根据uuid查询uid
	 * @param uid
	 * @return
	 */
	public String selectUid(String uuid) {
		String sql = "select uid from user where uuid = ?";
		String result = null;
		try {
			result = DBUtil.select(sql, uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
	

	/**
	 * 根据openid返回user对象
	 * @param openId
	 * @return
	 */
	public User selectUser(String openId) {
		String sql = "select nickname,uid,uuid from user where openId = ?";
		User user = new User();
		try {
			 user = DBUtil.select(sql, new BeanHandler<User>(User.class), openId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user; 
	}
	
	/**
	 * 根据uid查询nickName
	 * @param uid
	 * @return
	 */
	public String selectnickName(int uid) {
		String result = null;
		String sql = "select nickName from user where uid = ?";
		try {
			result = DBUtil.select(sql, uid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
}