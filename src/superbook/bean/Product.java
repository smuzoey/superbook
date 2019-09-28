package superbook.bean;

import java.util.Date;

public class Product {
	private int pid;
	private int cid;//产品类别
	private String isbn;//书籍信息
	private double promotePrice;//出售价格
	private Date createDate;//创建日期
	private String subTitle;//产品描述
	private int degree;//产品新旧成度
	
	public Product() {
		super();
	}

	public Product(int pid, int cid, String isbn, float promotePrice, Date createDate, String subTitle, int degree) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.isbn = isbn;
		this.promotePrice = promotePrice;
		this.createDate = createDate;
		this.subTitle = subTitle;
		this.degree = degree;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(double d) {
		this.promotePrice = d;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", cid=" + cid + ", isbn=" + isbn + ", promotePrice=" + promotePrice
				+ ", createDate=" + createDate + ", subTitle=" + subTitle + ", degree=" + degree + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + degree;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + pid;
		result = (int) (prime * result + Double.doubleToLongBits(promotePrice));
		result = prime * result + ((subTitle == null) ? 0 : subTitle.hashCode());
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
		Product other = (Product) obj;
		if (cid != other.cid)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (degree != other.degree)
			return false;
		if (isbn != other.isbn)
			return false;
		if (pid != other.pid)
			return false;
		if (Double.doubleToLongBits(promotePrice) != Double.doubleToLongBits(other.promotePrice))
			return false;
		if (subTitle == null) {
			if (other.subTitle != null)
				return false;
		} else if (!subTitle.equals(other.subTitle))
			return false;
		return true;
	}

	
}
