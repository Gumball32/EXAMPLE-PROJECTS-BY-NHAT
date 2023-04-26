package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Product;
import context.DBContext;

public class ProductDAO {
	public List<Product> getProducts(int start, int total) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<Product> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from (select *, ROW_NUMBER() OVER(ORDER BY id) as row from SellProduct where status=1 and amount > 0) as r"
					+ 								" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			
			while (rs.next()) {
				Product post = new Product();
				post.setName(rs.getString("name"));
				post.setDescription(rs.getString("description"));
				post.setPost_date(rs.getDate("post_date"));
				post.setPrice(rs.getInt("price"));
				post.setId(rs.getInt("id"));
				post.setImage(rs.getString("image"));
				post.setStatus(rs.getInt("status"));
				post.setAmount(rs.getInt("amount"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Product> getProductsWithName(int start, int total, String name) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<Product> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from (select *, ROW_NUMBER() OVER(ORDER BY id) as row from SellProduct where status=1 and amount > 0 and name like '%"+ name +"%') as r"
					+ 								" where r.row > " + (start - 1) + " and r.row <=" + (start + total));
			
			while (rs.next()) {
				Product post = new Product();
				post.setName(rs.getString("name"));
				post.setDescription(rs.getString("description"));
				post.setPost_date(rs.getDate("post_date"));
				post.setPrice(rs.getInt("price"));
				post.setId(rs.getInt("id"));
				post.setImage(rs.getString("image"));
				post.setStatus(rs.getInt("status"));
				post.setAmount(rs.getInt("amount"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getTotalProduct() throws Exception {
		int size = 0;
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		 try {
			Statement statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery("select * from SellProduct where status=1");
			
			while (rs2.next()) {
				size++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return size;
	}

	public Product getProduct(String id) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		List<Product> list = new ArrayList<>();		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * "
					+ "								from SellProduct "
					+ 								"where id=" + id);
			
			while (rs.next()) {
				Product post = new Product();
				post.setName(rs.getString("name"));
				post.setDescription(rs.getString("description"));
				post.setPost_date(rs.getDate("post_date"));
				post.setPrice(rs.getInt("price"));
				post.setId(rs.getInt("id"));
				post.setImage(rs.getString("image"));
				post.setStatus(rs.getInt("status"));
				post.setAmount(rs.getInt("amount"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list.get(0);
	}
	
	public int deleteProduct(String id) throws Exception {
		int rowsAffedted = 0;
		Connection connection = new DBContext().getConnection();
		try {
			PreparedStatement st = connection.prepareStatement("update SellProduct set status=0 where id=?");
	        st.setString(1,id);
	        rowsAffedted = st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rowsAffedted != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public int updateProduct(Product product) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			sql = "update SellProduct "
					+ "set name = '" + product.getName()
					+ "',description = '" + product.getDescription()
					+ "',amount = " + product.getAmount()
					+ ",price = " + product.getPrice()
					+ ",image = '" + product.getImage()
					+ "' where id=" + product.getId();
			rs = statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public int updateProductAmount(int productId) throws Exception {
		Connection connection = new DBContext().getConnection();
		int rs = 0;
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			sql = "update SellProduct "
					+ "set amount = amount - 1"
					+ " where id=" + productId;
			rs = statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
	
	public int CreateProduct(Product product) throws Exception {
		DBContext context = DBContext.getInstance();
		Connection connection = context.getConnection();
		int rs = 0;
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			String postDate = mdyFormat.format(product.getPost_date());
			sql = "insert into SellProduct values('" + product.getName() + "'," + "'" + product.getDescription() + "'," + "'" + postDate + "'," + "" + product.getPrice() + "," + "'" + product.getImage() + "'," + "" + 1 + "," + product.getAmount() + ")";
			System.out.println(sql);
			rs = statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rs != 0) {
			return 1;
		}
		
		return 0;
	}
}
