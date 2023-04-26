package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.IPayment;
import Interface.IPaymentDAO;
import Model.BankPayment;
import context.DBContext;

public class BankPaymentDAO implements IPaymentDAO {
	public int order(IPayment ipayment, int orderId) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		BankPayment payment = (BankPayment) ipayment;
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into BankPayment (image, fee, status, address, phoneNumber, orderId) values(?,?,?,?,?,?)");
			st.setString(1, payment.getImage());
			st.setInt(2, payment.getFee());
			st.setInt(3, payment.getStatus());
			st.setString(4, payment.getAddress());
			st.setString(5, payment.getPhoneNumber());
			st.setInt(6, orderId);
			rs = st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public BankPayment getOrder(String orderId) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<BankPayment> list = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from BankPayment "
					+ 								"where orderId=" + orderId);
			
			while (rs.next()) {
				BankPayment payment = new BankPayment();
				payment.setId(rs.getInt("id"));
				payment.setAddress(rs.getString("address"));
				payment.setStatus(rs.getInt("status"));
				payment.setOrderId(rs.getInt("orderId"));
				payment.setPhoneNumber(rs.getString("phoneNumber"));
				payment.setImage(rs.getString("image"));
				list.add(payment);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
