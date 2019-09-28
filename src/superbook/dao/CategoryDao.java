package superbook.dao;

import superbook.bean.Category;
import superbook.util.DBUtil;

public class CategoryDao {
	
	/**
	 * 添加分类的类别
	 * @param category
	 */
	public void add(Category category) {
		String sql = "insert into Category(cid, name) values(?,?);";
		try {
			DBUtil.update(sql, category.getCid(), category.getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据cid删除类别
	 * @param cid
	 */
	public void delete(int cid) {
		String sql = "delete from Category where cid = ?;";
		try {
			DBUtil.update(sql, cid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据cid查询类名
	 * @param cid
	 * @return
	 */
	public String selectname(int cid) {
		String result = null;
		String sql = "select name from Category where cid = ?;";
		try {
			result = DBUtil.select(sql, cid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int selectcid(String name) {
		String result = null;
		String sql = "select cid from Category where name = ?;";
		try {
			result = DBUtil.select(sql, name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(result);
	}
}
