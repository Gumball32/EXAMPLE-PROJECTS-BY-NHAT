package beans;

public class PostImage {
	int ID;
	String URL;
	int postID;
	int role;
	
	public PostImage() {}
	public PostImage(String uRL, int postID, int role) {
		super();
		URL = uRL;
		this.postID = postID;
		this.role = role;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
