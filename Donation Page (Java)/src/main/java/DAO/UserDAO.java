package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beans.CharityPost;
import beans.User;
import context.DBContext;

public class UserDAO {
	public String createAccount(User user) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into Users values(?,?,?, ?,?,?,?,?,?,?,?,?)");
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getGender());
			st.setString(4, user.getIDCard());
			
			//convert date to string
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String date1 = mdyFormat.format(user.getDOB());
			
			st.setString(5, date1);
			st.setString(6, user.getUsername());
			st.setString(7, user.getPassword());
			st.setString(8, user.getEmail());
			st.setString(9, user.getPhoneNumber());
			st.setInt(10, user.getRole());
			st.setInt(11, user.getStatus());
			st.setString(12, user.getMainImage());
			rs = st.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public String deleteUser(String id) throws Exception {
		int rowsAffedted = 0;
		Connection connection = new DBContext().getConnection();
		try {
			PreparedStatement st = connection.prepareStatement("update Users set Status=0 where ID=?");
			st.setString(1, id);
			rowsAffedted = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rowsAffedted != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public String updateAccount(User user, String ID) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("update Users set LastName=?, FirstName=?, Gender=?,"
																+ " IDCard=?, DOB=?, Username=?, Password=?, Email=?,"
																+ " PhoneNumber=?, Image=?"
																+ " where ID=?");
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getGender());
			st.setString(4, user.getIDCard());
			
			//convert date to string
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String date1 = mdyFormat.format(user.getDOB());
			
			st.setString(5, date1);
			st.setString(6, user.getUsername());
			st.setString(7, user.getPassword());
			st.setString(8, user.getEmail());
			st.setString(9, user.getPhoneNumber());
			st.setString(10, user.getMainImage());
			st.setString(11, ID);
			rs = st.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public String updatePassword(int id, String password) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("update Users set Password=? "
					+ " where ID=?");
			st.setString(1, password);
			st.setInt(2, id);
			rs = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public List<User> findUsers(String username, String IDCard) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<User> list = new ArrayList<>();	
		try {
			PreparedStatement st = null;
			if (username.equals("None") && !IDCard.equals("0")) {
				st = connection.prepareStatement("select * from Users where IDCard=?");
				st.setString(1, IDCard);
			} else if (IDCard.equals("0") && !username.equals("None")) {
				st = connection.prepareStatement("select * from Users where Username=?");
				st.setString(1, username);
			} else {
				st = connection.prepareStatement("select * from Users where Username=? and IDCard=? ");
				st.setString(1, username);
				st.setString(2, IDCard);
			}
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setID(rs.getInt("ID"));
				user.setIDCard(rs.getString("IDCard"));
				user.setDOB(rs.getDate("DOB"));
				user.setEmail(rs.getString("Email"));
				user.setGender(rs.getString("Gender"));
				user.setPassword(rs.getString("Password"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getInt("Role"));
				user.setStatus(rs.getInt("Status"));
				user.setUsername(rs.getString("Username"));
				user.setMainImage(rs.getString("Image"));
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int checkUsername(String usename) throws Exception {
		Connection connection = new DBContext().getConnection();
		ResultSet rs = null;
		int size = 0;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where Username like '" + usename + "'");
			while (rs.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}
	
	public User getUser(String username) throws Exception {
		Connection connection = new DBContext().getConnection();
		ResultSet rs = null;
		User user = null;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where Username like '" + username + "'");
			while (rs.next()) {
				user = new User();
				user.setID(rs.getInt("ID"));
				user.setEmail(rs.getString("Email"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setGender(rs.getString("Gender"));
				user.setIDCard(rs.getString("IDCard"));
				user.setDOB(rs.getDate("DOB"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getInt("Role"));
				user.setStatus(rs.getInt("Status"));
				user.setMainImage(rs.getString("Image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User getUserID(String id) throws Exception {
		Connection connection = new DBContext().getConnection();
		ResultSet rs = null;
		User user = null;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where ID=" + id + "");
			while (rs.next()) {
				user = new User();
				user.setID(rs.getInt("ID"));
				user.setEmail(rs.getString("Email"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setGender(rs.getString("Gender"));
				user.setIDCard(rs.getString("IDCard"));
				user.setDOB(rs.getDate("DOB"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getInt("Role"));
				user.setStatus(rs.getInt("Status"));
				user.setMainImage(rs.getString("Image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<User> getAllUsers(int start, int total) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<User> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from (select *, ROW_NUMBER() OVER(ORDER BY ID) as row from Users where Status=1) as r"
					+ 										" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			while (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setID(rs.getInt("ID"));
				user.setIDCard(rs.getString("IDCard"));
				user.setDOB(rs.getDate("DOB"));
				user.setEmail(rs.getString("Email"));
				user.setGender(rs.getString("Gender"));
				user.setPassword(rs.getString("Password"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getInt("Role"));
				user.setStatus(rs.getInt("Status"));
				user.setUsername(rs.getString("Username"));
				user.setMainImage(rs.getString("Image"));
				
				list.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	public int getNoUsers() throws Exception {
		int result = 0;
		try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Users");
			
			while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> login(String username, String password) throws Exception {
		Connection connection = new DBContext().getConnection();		
		List<User> list = new ArrayList<>();
		ResultSet rs;
		try {
			PreparedStatement st = connection.prepareStatement("select * from Users where Username=? and Password=?");
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setID(rs.getInt("ID"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setDOB(rs.getDate("DOB"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setGender(rs.getString("Gender"));
				user.setIDCard(rs.getString("IDCard"));
				user.setEmail(rs.getString("Email"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getInt("Role"));
				user.setStatus(rs.getInt("Status"));
				user.setMainImage(rs.getString("Image"));
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
