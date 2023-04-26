package Model;

import java.sql.Date;

import Interface.IProduct;

public class Product implements IProduct {
	private int id;
	private String name;
	private int price;
	private String description;
	private Date post_date;
	private String image;
	private int status;
	private int amount;
	
	public Product(int id, String name, int price, String description, Date post_date, String image, int status, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.post_date = post_date;
		this.image = image;
		this.status = status;
		this.amount = amount;
	}
	
	public Product(String name, int price, String description, Date post_date, String image, int status, int amount) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.post_date = post_date;
		this.image = image;
		this.status = status;
		this.amount = amount;
	}
	
	public Product() {}

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int type() {
		return 1;
	}

	@Override
	public int getTotalPrice() {
		return getTotalPrice() * 1;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
