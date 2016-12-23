package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager {

	private Connection conn;
	private final String CONN_STRING;
	private final String USERNAME;
	private final String PASSWORD;

	public DAOManager(String username, String password) throws Exception {
		try {
			this.USERNAME = username;
			this.PASSWORD = password;
			CONN_STRING = "jdbc:mysql://localhost/bookstore";
		} catch (Exception e) {
			throw e;
		}
	}

	public void open() throws SQLException {
		try {
			if (this.conn == null) {
				this.conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public void close() throws SQLException {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	public Connection getConnection(){
		return this.conn;
	}
}
