package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

	private final String tablename;
	private Connection conn;

	
	public AbstractDAO(String tablename, Connection conn){
		this.tablename = tablename;
		this.conn = conn;

	}
	public String getTablename(){
		return this.tablename;
	}
	public Connection getConnection(){
		return this.conn;
	}
	public ResultSet getTableDesc() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT * FROM " + this.getTablename() + " ORDER BY id DESC";				
		try{
			p = this.conn.prepareStatement(query);
			rs = p.executeQuery();
			return rs;
		}
		catch(SQLException e){
			throw e;
		}		
	}
	public ResultSet getTableAsce() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT * FROM " + this.getTablename() + " ORDER BY id";				
		try{
			p = this.conn.prepareStatement(query);
			rs = p.executeQuery();
			return rs;
		}
		catch(SQLException e){
			throw e;
		}		
	}

	public void delete(String id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "DELETE FROM " + this.getTablename() + " WHERE id = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}
	}
}
