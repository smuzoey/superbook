package superbook.bean;

public class Book {
	private String isbn;
	private String publisher;
	private String binding;//封装方式
	private int page;//页数
	private String author;
	private String paper;//纸质
	private String title;
	private Double price;//原价
	private String img;
	private String gist;//片段节选
	
	
	
	public Book() {
		super();
	}
	public Book(String isbn, String publisher, String binding, Integer page, String author, String paper, String title,
			Double price, String img, String gist) {
		super();
		this.isbn = isbn;
		this.publisher = publisher;
		this.binding = binding;
		this.page = page;
		this.author = author;
		this.paper = paper;
		this.title = title;
		this.price = price;
		this.img = img;
		this.gist = gist;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public int getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double l) {
		this.price = l;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getGist() {
		return gist;
	}
	public void setGist(String gist) {
		this.gist = gist;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((binding == null) ? 0 : binding.hashCode());
		result = prime * result + ((gist == null) ? 0 : gist.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + page;
		result = prime * result + ((paper == null) ? 0 : paper.hashCode());
		result = (int) (prime * result + Double.doubleToLongBits(price));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (binding == null) {
			if (other.binding != null)
				return false;
		} else if (!binding.equals(other.binding))
			return false;
		if (gist == null) {
			if (other.gist != null)
				return false;
		} else if (!gist.equals(other.gist))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (page != other.page)
			return false;
		if (paper == null) {
			if (other.paper != null)
				return false;
		} else if (!paper.equals(other.paper))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", publisher=" + publisher + ", binding=" + binding + ", page=" + page
				+ ", author=" + author + ", paper=" + paper + ", title=" + title + ", price=" + price + ", img=" + img
				+ ", gist=" + gist + "]";
	}
	
	
	
	
}
