package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * データベース操作用クラス
 */
public class DBManager {

	/**
	 * データベースと接続するメソッド
	 * @return Connection
	 */
	public static Connection getConnection() {			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con;
			con = DriverManager.getConnection(
			  "",
			  "",
			  ""
			);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
		
	/**
	 * データベースとの接続を閉じるメソッド
	 * @param con
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * データベースとの接続とPreparedStatementを閉じるメソッド
	 * @param ps
	 * @param con
	 */
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
