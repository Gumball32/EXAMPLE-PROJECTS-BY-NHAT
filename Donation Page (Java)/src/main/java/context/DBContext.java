package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private final String serverName = "LAPTOP-2LCB86B1\\SQLEXPRESS";
	private final String dbName = "Donation";
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
}
	