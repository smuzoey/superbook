package superbook.bean;

public class Box {
	private int bid;
	private int mid;//机器号
	private int boxState;//箱子状态
	private int size;//箱子大小
	public Box() {
		super();
	}
	public Box(Integer bid, Integer mid, Integer boxState, Integer size) {
		super();
		this.bid = bid;
		this.mid = mid;
		this.boxState = boxState;
		this.size = size;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public int getBoxState() {
		return boxState;
	}
	public void setBoxState(Integer boxState) {
		this.boxState = boxState;
	}
	public int getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + boxState;
		result = prime * result + mid;
		result = prime * result + size;
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
		Box other = (Box) obj;
		if (bid != other.bid)
			return false;
		if (boxState != other.boxState)
			return false;
		if (mid != other.mid)
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Box [bid=" + bid + ", mid=" + mid + ", boxState=" + boxState + ", size=" + size + "]";
	}
	
}
