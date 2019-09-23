package superbook.dao;

import superbook.bean.Machine;
import superbook.util.DBUtil;

public class MachineDao {
	
	/**
	 * 添加机器信息
	 * @param machine
	 */
	public void add(Machine machine) {
		String sql = "insert into Machine(mid, position, machineState) values(?,?,?);";
		try {
			DBUtil.update(sql, machine.getMid(),machine.getPosition(), machine.getMachineState());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据mid删除机器信息
	 * @param mid
	 */
	public void delete(int mid) {
		String sql = "delete from Machine where mid = ?;";
		try {
			DBUtil.update(sql, mid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据mid返回position位置
	 * @param mid
	 * @return
	 */
	public String selectPosition(int mid) {
		String sql = "select position from Machine where mid = ?;";
		try {
			DBUtil.select(sql, mid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 根据mid返回箱子状态
	 * @param mid
	 * @return
	 */
	public String selectMachineState(int mid) {
		String sql = "select machineState from Machine where mid = ?;";
		try {
			DBUtil.select(sql, mid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
}
