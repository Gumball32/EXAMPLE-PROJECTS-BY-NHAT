package beans;

import java.sql.Date;

public class Donation {
	private int userID;
	private int postID;
	private long amount;
	private String description;
	private Date createdDate;
	public Donation(int userID, int postID, long amount, String description, Date createdDate) {
		super();
		this.userID = userID;
		this.postID = postID;
		this.amount = amount;
		this.description = description;
		this.createdDate = createdDate;
	}
	
	public Donation() {
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
