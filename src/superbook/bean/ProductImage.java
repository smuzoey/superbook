package superbook.bean;

public class ProductImage {
	private int piid;
	private int pid;
	private String type;//图片类型
	private String imgposition;//图片位置
	public ProductImage() {
		super();
	}
	public ProductImage(int piid, int pid, String type, String imgposition) {
		super();
		this.piid = piid;
		this.pid = pid;
		this.type = type;
		this.imgposition = imgposition;
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
	public String getImgposition() {
		return imgposition;
	}
	public void setImgposition(String imgposition) {
		this.imgposition = imgposition;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imgposition == null) ? 0 : imgposition.hashCode());
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
		if (imgposition == null) {
			if (other.imgposition != null)
				return false;
		} else if (!imgposition.equals(other.imgposition))
			return false;
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
		return "ProductImage [piid=" + piid + ", pid=" + pid + ", type=" + type + ", imgposition=" + imgposition + "]";
	}
	
}
