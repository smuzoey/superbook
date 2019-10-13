package superbook.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import superbook.bean.Review;
import superbook.util.DBUtil;
import superbook.util.DateUtil;

//已测试

public class ReviewDao {

	/**
	 * 添加评论信息
	 * @param r
	 */
	public void add(Review r) {
		String sql = "insert into Review(rid,pid,uid,content,createDate) values(?,?,?,?,?);";
		try {
			DBUtil.update(sql, r.getRid(),r.getPid(),r.getUid(),r.getContent(),DateUtil.dtot(r.getCreateDate()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除评论信息
	 * @param rid
	 */
	public void delete(int rid) {
		String sql = "delete from Review where rid = ?;";
		try {
			DBUtil.update(sql, rid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过rid返回评论表全部信息
	 * @param rid
	 * @return
	 */
	public Review selectByRid(int rid) {
		String sql = "select * from Review where rid = ?;";
		Review r = new Review();
		try {
			r = DBUtil.select(sql, new BeanHandler<Review>(Review.class),rid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(r);
		return r;
	}
	
	public Review selectByPid(int pid) {
		String sql = "select * from Review where pid=?;";
		Review review = new Review();
		try {
			review = DBUtil.select(sql, new BeanHandler<Review>(Review.class),pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return review;
	}

}
