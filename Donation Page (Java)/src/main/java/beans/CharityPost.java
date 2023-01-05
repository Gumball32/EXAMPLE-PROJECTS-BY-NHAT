package beans;

import java.util.Date;

public class CharityPost {
	private int ID;
	private String name;
	private String description;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	private Date createdDate;
	private Date startDate;
	private Date endDate;
	private String mainImage;
	
	public CharityPost(String name, String description, Date createdDate, Date startDate, Date endDate,
			String mainImage) {
		super();
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainImage = mainImage;
	}
	public CharityPost(int iD, String name, String description, Date createdDate, Date startDate, Date endDate) {
		super();
		ID = iD;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public CharityPost(String name, String description, Date createdDate, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public CharityPost() {
		super();
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	
}
