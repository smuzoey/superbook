package superbook.bean;
/**
 * 
 * @author 再几何
 * 购物车信息
 */
public class OrderItem {
	private int id;
	private int uid;
	private int oid;//订单信息
	private int number; //购买数量
	public OrderItem() {
		super();
	}
	public OrderItem(Integer id, Integer uid, Integer oid, Integer number) {
		super();
		this.id = id;
		this.uid = uid;
		this.oid = oid;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + oid;
		result = prime * result + uid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id != other.id)
			return false;
		if (number != other.number)
			return false;
		if (oid != other.oid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", uid=" + uid + ", oid=" + oid + ", number=" + number + "]";
	}
	
	
}
