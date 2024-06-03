package model;

public class Book {
	private int id; 
	private String name; 
	private String author; 
	private int source; 
	//private Integer indexid; 
	private String category; 
	private String area;

	public Book() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Book(int id, String name, String author, int source, String category) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.source = source;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	
	
}
