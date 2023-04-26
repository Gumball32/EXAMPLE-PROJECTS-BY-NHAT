package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.IPayment;
import Interface.IPaymentDAO;
import Model.CODPayment;
import context.DBContext;

public class CODPaymentDAO implements IPaymentDAO {
	public int order(IPayment ipayment, int orderId) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		CODPayment payment = (CODPayment) ipayment;
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into CODPayment (address, phoneNumber, status, orderId, fee) values(?,?,?,?,?)");
			st.setString(1, payment.getAddress());
			st.setString(2, payment.getPhoneNumber());
			st.setInt(3, payment.getStatus());
			st.setInt(4, orderId);
			st.setInt(5, payment.getFee());
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
	
	public CODPayment getOrder(String orderId) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<CODPayment> list = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from CODPayment "
					+ 								"where orderId=" + orderId);
			
			while (rs.next()) {
				CODPayment payment = new CODPayment();
				payment.setId(rs.getInt("id"));
				payment.setAddress(rs.getString("address"));
				payment.setStatus(rs.getInt("status"));
				payment.setOrderId(rs.getInt("orderId"));
				payment.setPhoneNumber(rs.getString("phoneNumber"));
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
