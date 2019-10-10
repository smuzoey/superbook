package superbook.bean;

import java.util.Date;

public class Orders {
	private Integer oid;
	private Integer bid;//箱子号
	private Integer uid;//用户信息，卖家
	private String orderCode;//订单号
	private Integer receiver;//买家
	private String userMessage;//卖家信息
	private String phone;//联系方式
	private Date createTime;//创建订单信息
	private Date payDate;//支付日期
	private Date deliverDate;//发货日期
	private Date confirmDate;//确认收货日期
	private Integer orderState;//订单状态
	private Integer pid;//产品信息
	public Orders() {
		super();
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((confirmDate == null) ? 0 : confirmDate.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((deliverDate == null) ? 0 : deliverDate.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((orderCode == null) ? 0 : orderCode.hashCode());
		result = prime * result + ((orderState == null) ? 0 : orderState.hashCode());
		result = prime * result + ((payDate == null) ? 0 : payDate.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((userMessage == null) ? 0 : userMessage.hashCode());
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
		Orders other = (Orders) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (confirmDate == null) {
			if (other.confirmDate != null)
				return false;
		} else if (!confirmDate.equals(other.confirmDate))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (deliverDate == null) {
			if (other.deliverDate != null)
				return false;
		} else if (!deliverDate.equals(other.deliverDate))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (orderCode == null) {
			if (other.orderCode != null)
				return false;
		} else if (!orderCode.equals(other.orderCode))
			return false;
		if (orderState == null) {
			if (other.orderState != null)
				return false;
		} else if (!orderState.equals(other.orderState))
			return false;
		if (payDate == null) {
			if (other.payDate != null)
				return false;
		} else if (!payDate.equals(other.payDate))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (userMessage == null) {
			if (other.userMessage != null)
				return false;
		} else if (!userMessage.equals(other.userMessage))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", bid=" + bid + ", uid=" + uid + ", orderCode=" + orderCode + ", receiver="
				+ receiver + ", userMessage=" + userMessage + ", phone=" + phone + ", createTime=" + createTime
				+ ", payDate=" + payDate + ", deliverDate=" + deliverDate + ", confirmDate=" + confirmDate
				+ ", orderState=" + orderState + ", pid=" + pid + "]";
	}

	
	
	
}
