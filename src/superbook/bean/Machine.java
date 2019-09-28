package superbook.bean;

public class Machine {
	private int mid;
	private String position;
	private int machineState;
	public Machine() {
		super();
	}
	public Machine(Integer mid, String position, Integer machineState) {
		super();
		this.mid = mid;
		this.position = position;
		this.machineState = machineState;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getMachineState() {
		return machineState;
	}
	public void setMachineState(Integer machineState) {
		this.machineState = machineState;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + machineState;
		result = prime * result + mid;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Machine other = (Machine) obj;
		if (machineState != other.machineState)
			return false;
		if (mid != other.mid)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Machine [mid=" + mid + ", position=" + position + ", machineState=" + machineState + "]";
	}
	
	
	
}
