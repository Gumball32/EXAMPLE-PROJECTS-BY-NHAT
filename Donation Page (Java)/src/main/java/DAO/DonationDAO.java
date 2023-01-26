package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import beans.CharityPost;
import beans.Donation;
import context.DBContext;

public class DonationDAO {
	public String donate(Donation donation) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into Donate values(?,?,?,?,?)");
			st.setInt(1, donation.getUserID());
			st.setInt(2, donation.getPostID());
			st.setLong(3, donation.getAmount());
			st.setString(4, donation.getDescription());
			st.setDate(5, donation.getCreatedDate());
			rs = st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public List<Donation> getAllDonate(int start, int total, String userId) throws Exception {
		Connection connection = new DBContext().getConnection();
		List<Donation> list = new ArrayList<>();	
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from (select *, ROW_NUMBER() OVER(ORDER BY UserID, PostID) as row from Donate where UserID=" + userId +") as r"
					+ 								" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			
			while (rs.next()) {
				Donation donate = new Donation();
				donate.setPostID(rs.getInt("PostID"));
				donate.setUserID(rs.getInt("UserID"));
				donate.setAmount(rs.getInt("Amount"));
				donate.setDescription(rs.getString("Description"));
				donate.setCreatedDate(rs.getDate("Created_Date"));
				
				list.add(donate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String deleteDonate(String userID, String postID) throws Exception {
		int rowsAffedted = 0;
		Connection connection = new DBContext().getConnection();
		try {
			PreparedStatement st = connection.prepareStatement("DELETE FROM Donate WHERE UserID=? and PostID=?");
	        st.setString(1,userID);
	        st.setString(2,postID);
	        rowsAffedted = st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rowsAffedted != 0) {
			return "Success";
		}
		
		return "Failed";
	}
	
	public int getTotalDonation() throws Exception {
		int size = 0;
		 try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery("select * from Donate");
			
			while (rs2.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}
	
	public int getTotalAmountDonation(String id) throws Exception {
		int amount = 0;
		 try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery("select * from Donate where PostID=" + id);
			
			while (rs2.next()) {
				amount += rs2.getInt("Amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return amount;
	}
}
