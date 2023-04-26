package Model;

public class OrderItem {
	private int id;
	private int productId;
	private int price;
	private int amount;
	private String name;
	private String image;
	private int status;

	public OrderItem() {}
	
	public OrderItem(int product, int price, int amount, String name, String image, int status) {
		super();
		this.productId = product;
		this.price = price;
		this.amount = amount;
		this.name = name;
		this.image = image;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductID() {
		return productId;
	}
	public void setProductID(int product) {
		this.productId = product;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
}
