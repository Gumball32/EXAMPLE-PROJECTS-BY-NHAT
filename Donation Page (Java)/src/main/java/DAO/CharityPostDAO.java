package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import beans.CharityPost;
import beans.PostImage;
import context.DBContext;

public class CharityPostDAO {
	private int noOfPosts;
	
	public int getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	
	public String addImage(int postID, String url, int role) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into PostImages values(?,?,?)");
			st.setInt(1, postID);
			st.setString(2, url);
			st.setInt(3, role);
			rs = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}

	public String createPost(CharityPost post) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String startDate = mdyFormat.format(post.getStartDate());
			String endDate = mdyFormat.format(post.getEndDate());
			String createdDate = mdyFormat.format(post.getCreatedDate());
			sql = "insert into CharityPost values('" + post.getName() + "'," + "'" + post.getDescription() + "'," + "'" + startDate + "'," + "'" + endDate + "'," + "'" + createdDate + "'," + "'" + 1 + "','" + post.getMainImage() + "')";
			rs = statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public String deletePost(String id) throws Exception {
		int rowsAffedted = 0;
		Connection connection = new DBContext().getConnection();
		try {
			PreparedStatement st = connection.prepareStatement("update CharityPost set Status=0 where ID=?");
	        st.setString(1,id);
	        rowsAffedted = st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rowsAffedted != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public String updatePost(CharityPost post) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String startDate = mdyFormat.format(post.getStartDate());
			String endDate = mdyFormat.format(post.getEndDate());
			sql = "update CharityPost "
					+ "set Name = '" + post.getName()
					+ "',Description = '" + post.getDescription()
					+ "',StartDate = '" + startDate
					+ "',EndDate = '" + endDate
					+ "',Image = '" + post.getMainImage()
					+ "' where ID=" + post.getID();
			rs = statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return sql;
	}
	
	public int endPost(String id) throws Exception {
		int rs = 0;
		String sql = "";
		try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			long millis=System.currentTimeMillis();  
		    Date date = new java.sql.Date(millis);
		    SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String endDate = mdyFormat.format(date);
			sql = "update CharityPost "
					+ " set EndDate = '" + endDate
					+ "' where ID=" + id;
			rs = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public int getTotalPost() throws Exception {
		int size = 0;
		 try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery("select * from CharityPost");
			
			while (rs2.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}
	
	public List<CharityPost> getPosts(int start, int total) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<CharityPost> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from (select *, ROW_NUMBER() OVER(ORDER BY ID) as row from CharityPost where Status=1) as r"
					+ 								" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			
			while (rs.next()) {
				CharityPost post = new CharityPost();
				post.setName(rs.getString("Name"));
				post.setDescription(rs.getString("Description"));
				post.setStartDate(rs.getDate("StartDate"));
				post.setEndDate(rs.getDate("EndDate"));
				post.setID(rs.getInt("ID"));
				post.setMainImage(rs.getString("Image"));
				
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public CharityPost getPost(String id) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<CharityPost> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from CharityPost "
					+ 								"where ID=" + id);
			
			while (rs.next()) {
				CharityPost post = new CharityPost();
				post.setName(rs.getString("Name"));
				post.setDescription(rs.getString("Description"));
				post.setStartDate(rs.getDate("StartDate"));
				post.setEndDate(rs.getDate("EndDate"));
				post.setID(rs.getInt("ID"));
				post.setMainImage(rs.getString("Image"));
				post.setStatus(rs.getInt("Status"));
				
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list.get(0);
	}
	
	public List<CharityPost> getPostWithName(String name) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<CharityPost> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from CharityPost "
					+ 								"where Name like '%" + name + "%'");
			
			while (rs.next()) {
				CharityPost post = new CharityPost();
				post.setName(rs.getString("Name"));
				post.setDescription(rs.getString("Description"));
				post.setStartDate(rs.getDate("StartDate"));
				post.setEndDate(rs.getDate("EndDate"));
				post.setID(rs.getInt("ID"));
				post.setMainImage(rs.getString("Image"));
				post.setStatus(rs.getInt("Status"));
				
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public PostImage getMainImage(int id) throws Exception {
		Connection connection = new DBContext().getConnection();
		PostImage post = new PostImage();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from PostImages "
					+ 								"where ID=" + Integer.toString(id) + " and Role=1");
			
			while (rs.next()) {
				post.setPostID(rs.getInt("PostID"));
				post.setURL(rs.getString("URL_Path"));
				post.setRole(rs.getInt("Role"));
				post.setID(rs.getInt("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return post;
	}
}
