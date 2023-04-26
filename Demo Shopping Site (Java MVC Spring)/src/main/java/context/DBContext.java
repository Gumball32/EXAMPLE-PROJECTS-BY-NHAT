package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private static DBContext connection;
	
	public static synchronized DBContext getInstance() {
        if (connection == null) {
        	connection = new DBContext();
        }
        return connection;
    }
	
	private final String serverName = "LAPTOP-2LCB86B1\\SQLEXPRESS";
	private final String dbName = "Shoppeoo";
	private final String portNumber = "1433";
	private final String instance = null;
	private final String userID = "sa";
	private final String password = "password";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "" + instance
				+ ";databaseName=" + dbName + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
		if (instance == null || instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
		}
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) {
		DBContext conetxt = new DBContext();
		try {
			Connection conn = conetxt.getConnection();
			System.out.print(conn.getMetaData() + " " + conn.getCatalog());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	