package Model;

import java.sql.Date;

public class Order {
	private int id;
	private int userId;
	private Date orderDate;
	private int totalCost;
	private int status;
	
	public Order(int userId, Date orderDate, int totalCost, int status) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
