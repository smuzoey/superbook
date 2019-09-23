package superbook.bean;

public class ProductImage {
	private int piid;
	private int pid;
	private String type;//图片类型
	public ProductImage() {
		super();
	}
	public ProductImage(int piid, int pid, String type) {
		super();
		this.piid = piid;
		this.pid = pid;
		this.type = type;
	}
	public int getPiid() {
		return piid;
	}
	public void setPiid(int piid) {
		this.piid = piid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pid;
		result = prime * result + piid;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ProductImage other = (ProductImage) obj;
		if (pid != other.pid)
			return false;
		if (piid != other.piid)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductImage [piid=" + piid + ", pid=" + pid + ", type=" + type + "]";
	}
	
}
