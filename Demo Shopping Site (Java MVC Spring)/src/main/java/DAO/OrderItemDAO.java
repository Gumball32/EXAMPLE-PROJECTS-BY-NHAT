package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.OrderItem;
import context.DBContext;

public class OrderItemDAO {
	public int insertOrderItem(OrderItem item, int orderId) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		int rs = 0;
		try {
			PreparedStatement st = connection.prepareStatement("insert into Order_Item (order_id, productId, price, quantity) values(?,?,?,?)");
			st.setInt(1, orderId);
			st.setInt(2, item.getProductID());
			st.setInt(3, item.getPrice());
			st.setInt(4, item.getAmount());
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
}
