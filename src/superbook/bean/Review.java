package superbook.bean;

import java.util.Date;

public class Review {
	private int rid;
	private int pid;
	private int uid;
	private String content;//回复内容
	private Date createDate;
	public Review() {
		super();
	}
	public Review(Integer rid, Integer pid, Integer uid, String content, Date createDate) {
		super();
		this.rid = rid;
		this.pid = pid;
		this.uid = uid;
		this.content = content;
		this.createDate = createDate;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Review [rid=" + rid + ", pid=" + pid + ", uid=" + uid + ", content=" + content + ", createDate="
				+ createDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + pid;
		result = prime * result + rid;
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
		Review other = (Review) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (pid != other.pid)
			return false;
		if (rid != other.rid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	
}
