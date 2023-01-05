package beans;

import java.util.Date;

public class User {
	private int ID;
	private String lastName;
	private String firstName;
	private String gender;
	private String idCard;
	private Date DOB;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private int role;
	private int status;
	private String mainImage;
	
	
	public User(String lastName, String firstName, String gender, String idCard, Date dOB, String username,
			String password, String email, String phoneNumber, int role, int status, String mainImage) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.idCard = idCard;
		DOB = dOB;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
		this.mainImage = mainImage;
	}

	public User(String lastName, String firstName, String gender, String idCard, Date dOB, String username,
			String password, String email, String phoneNumber, int role, int status) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.idCard = idCard;
		DOB = dOB;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}
	
	public User() {
		
	}
	
	public void setIDCard(String idcard) {
		this.idCard = idcard;
	}
	
	public String getIDCard() {
		return idCard;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
}
