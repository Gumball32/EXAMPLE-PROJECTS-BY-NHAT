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
}
