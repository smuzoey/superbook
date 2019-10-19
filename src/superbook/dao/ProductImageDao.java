package superbook.dao;


import java.util.ArrayList;
import java.util.List;

import superbook.bean.ProductImage;
import superbook.util.DBUtil;

public class ProductImageDao {
	
	/**
	 * 添加图片信息
	 * @param productImage
	 */
	public void add(ProductImage productImage) {
		String sql = "insert into productImage(piid,pid,type,imgposition) values (?,?,?,?);";
		try {
			DBUtil.update(sql, productImage.getPiid(),productImage.getPid(),productImage.getType(),productImage.getImgposition());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * 通过产品号返回类型，记录是否有该产品的图片内容
	 * @param pid
	 * @return
	 */
	public List<String> selectType(int pid) {
		List<String> result = new ArrayList<>();
		String sql = "select type from productimage where pid = ?;";
		try {
			result = DBUtil.selectList(sql, pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除该产品的图片信息
	 * @param pid
	 */
	public void delete(int pid) {
		String sql = "delete from productImage where pid = ?;";
		try {
			DBUtil.update(sql, pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
