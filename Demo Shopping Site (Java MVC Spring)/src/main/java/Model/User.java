package Model;

public class User {
	private int id;
	private String fullname;
	private String username;
	private String password;
	private String email;
	private int role;
	private int status;
	private String image;
	
	public User(String username, String fullname, String password, String email, int role, int status, String image) {
		super();
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.role = role;
		this.status = status;
		this.image = image;
		this.username = username;
	}

	public User(int id, String username, String fullname, String password, String email, int role, int status) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.role = role;
		this.status = status;
		this.username = username;
	}
	
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
