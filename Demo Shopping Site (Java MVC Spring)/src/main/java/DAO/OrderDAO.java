package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Order;
import context.DBContext;

public class OrderDAO {
	public int order(Order order) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		int affectedRows = 0;
		try {
			System.out.print(order.getOrderDate() + " " + order.getUserId() + " " + order.getStatus() + order.getTotalCost() + " ");
			PreparedStatement st = connection.prepareStatement("insert into Orders values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setDate(1, order.getOrderDate());
			st.setInt(2, order.getUserId());
			st.setInt(3, order.getStatus());
			st.setInt(4, order.getTotalCost());
			affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Insert failed, no rows affected.");
	        }
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			System.out.print(rs.getInt(1));
			if (rs.getInt(1) != 0) {
				return rs.getInt(1);
			}
			
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getTotalOrder() throws Exception {
		int size = 0;
		 try {
			Connection connection = new DBContext().getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery("select * from Orders");
			
			while (rs2.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}
	
	public List<Order> getAllOrders(int start, int total) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<Order> list = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from (select *, ROW_NUMBER() OVER(ORDER BY id) as row from Orders where status<>0) as r"
					+ 								" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setStatus(rs.getInt("status"));
				order.setTotalCost(rs.getInt("total_cost"));
				order.setUserId(rs.getInt("userId"));
				list.add(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public Order getOrder(String id) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<Order> list = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from Orders "
					+ 								"where id=" + id);
			
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setStatus(rs.getInt("status"));
				order.setTotalCost(rs.getInt("total_cost"));
				order.setUserId(rs.getInt("userId"));
				list.add(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list.get(0);
	}
	
	public int updateStatus(String id, String status) throws Exception {
		DBContext context = DBContext.getInstance();
		int rs = 0;
		Connection connection = context.getConnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "update Orders set status=" + status + " where id=" + id;
			System.out.println(sql);
			rs = statement.executeUpdate(sql);
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
