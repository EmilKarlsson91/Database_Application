package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract DAO class.
 * 
 * @author Emil Karlsson.
 *
 */
public abstract class AbstractDAO {

	private final String tablename;
	private Connection conn;

	/**
	 * Constructor
	 * 
	 * @param tablename
	 * @param conn
	 */
	public AbstractDAO(String tablename, Connection conn){
		this.tablename = tablename;
		this.conn = conn;

	}
	/**
	 * Method that returns tablename.
	 * 
	 * @return tableName
	 */
	public String getTablename(){
		return this.tablename;
	}
	/**
	 * Method that returns a connection.
	 * 
	 * @return conn
	 */
	public Connection getConnection(){
		return this.conn;
	}
	/**
	 * Method that returns a resultset from a whole table in descending order.
	 * 
	 * @return rs
	 * @throws SQLException
	 */
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
	/**
	 * Method that returns a resultset from a whole table in ascending order.
	 * 
	 * @return rs
	 * @throws SQLException
	 */
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
	/**
	 * Method that deletes a row with a specific id.
	 * 
	 * @param id
	 * @throws SQLException
	 */
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
