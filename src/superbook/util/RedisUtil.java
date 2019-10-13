package superbook.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	/**
	 * 添加到redis里面
	 * @param uuid
	 * @param openId
	 */
	public void setUser(String uuid, String openId) {
		Jedis jedis = new Jedis("localhost");//获取连接
		jedis.auth("123456");
		
		jedis.set(uuid, openId);//存储
		jedis.expire(uuid, 6000);//默认1分钟
	}
	
	/**
	 * 判断是否存在键
	 * @param uuid
	 * @return
	 */
	public boolean existsKey(String uuid) {
		Jedis jedis = new Jedis("localhost");
		jedis.auth("123456");
		
		jedis.exists(uuid);
		if(jedis.exists(uuid)) {
			jedis.expire(uuid, 6000);
			jedis.close();
			return true;
		} else {
			jedis.close();
			return false;
		}
	}
	
	/**
	 * 获取键值
	 * @param uuid
	 * @return
	 */
	public String getKey(String uuid) {//获取Openid
		Jedis jedis = new Jedis("localhost");
		jedis.auth("123456");
		
		if(jedis.exists(uuid)) {
			jedis.expire(uuid, 60000);
			jedis.close();
			return jedis.get(uuid);
		} else {
			jedis.close();
			return null;
		}
	}
}
