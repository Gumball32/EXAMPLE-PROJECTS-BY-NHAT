package Model;

import Interface.IPayment;

public class BankPayment implements IPayment {
	private int id;
	private String phoneNumber;
	private String address;
	private int orderId;
	private int status;
	private String image;
	
	public BankPayment(String phoneNumber, String address, int orderId, int status, String image) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.orderId = orderId;
		this.status = status;
		this.image = image;
	}
	public BankPayment() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public int getFee() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
