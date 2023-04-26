package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.User;
import context.DBContext;

public class UserDAO {
	public User getUser(String username) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		ResultSet rs = null;
		User user = null;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where username like '" + username + "'");
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("full_name"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getInt("status"));
				user.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public int createAccount(User user) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into Users values(?,?,?,?,?,?,?)");
			st.setString(1, user.getFullname());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setInt(5, user.getStatus());
			st.setInt(6, user.getRole());
			st.setString(7, user.getImage());
			rs = st.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public int checkUsername(String username) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		ResultSet rs = null;
		int size = 0;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where username like '" + username + "'");
			while (rs.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}

	public int updateAccount(User user, String ID) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("update Users set username=?, password=?, email=?,"
																+ " full_name=?, image=?"
																+ " where id=?");
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.setString(4, user.getFullname());
			st.setString(5, user.getImage());
			st.setString(6, ID);
			
			rs = st.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 0;
		}
		
		return 1;
	}
}
