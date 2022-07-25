package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

	public static Connection getConnection() {			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con;
			con = DriverManager.getConnection(
			  "jdbc:oracle:thin:@localhost:1521:XE",
			  "sssuser",
			  "systemsss"
			);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
		
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement ps, Connection con) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
