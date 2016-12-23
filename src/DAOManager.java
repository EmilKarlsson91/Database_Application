
/*
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAOManager {
	
		private final String CONN_STRING;
		private Connection conn;
		DataSource dataSrc;
		
	public DAOManager () throws Exception	{
		try {
			CONN_STRING = "jdbc:mysql://localhost/bookstore";
			InitialContext ctx = new InitialContext();
			dataSrc = (DataSource)ctx.bind("bbookstore", CONN_STRING);
			this.dataSrc = (DataSource)ctx.lookup(CONN_STRING);
			
		}
		catch(Exception e){
			throw e;
		}

	}
	public void open() throws SQLException{
			try{
				if(this.conn == null && this.conn.isClosed()){
					this.conn = dataSrc.getConnection();
				}
			}catch(SQLException e){
				throw e;
			}
		}
	public void close() throws SQLException{
		try{
			if(this.conn != null && !this.conn.isClosed()){
				this.conn.close();
			}
		}
		catch(SQLException e){
			throw e;
		}
	}
}*/
