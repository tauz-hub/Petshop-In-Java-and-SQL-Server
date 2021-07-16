package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
							"jdbc:sqlserver://localhost:1433;" + 
							"user=sa; password=123456;" + 
							"databaseName=Petshop_2CM_2DM;");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
