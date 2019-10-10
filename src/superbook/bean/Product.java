package superbook.bean;

import java.util.Date;

public class Product {
	private Integer pid;
	private Integer cid;//产品类别
	private String isbn;//书籍信息
	private Double promotePrice;//出售价格
	private Date createDate;//创建日期
	private String subTitle;//产品描述
	private Integer degree;//产品新旧成度
	
	public Product() {
		super();
	}

	
	public Product(Integer pid, Integer cid, String isbn, Double promotePrice, Date createDate, String subTitle,
			Integer degree) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.isbn = isbn;
		this.promotePrice = promotePrice;
		this.createDate = createDate;
		this.subTitle = subTitle;
		this.degree = degree;
	}



	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(Double promotePrice) {
		this.promotePrice = promotePrice;
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

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((promotePrice == null) ? 0 : promotePrice.hashCode());
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
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (promotePrice == null) {
			if (other.promotePrice != null)
				return false;
		} else if (!promotePrice.equals(other.promotePrice))
			return false;
		if (subTitle == null) {
			if (other.subTitle != null)
				return false;
		} else if (!subTitle.equals(other.subTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", cid=" + cid + ", isbn=" + isbn + ", promotePrice=" + promotePrice
				+ ", createDate=" + createDate + ", subTitle=" + subTitle + ", degree=" + degree + "]";
	}
	
	
}
