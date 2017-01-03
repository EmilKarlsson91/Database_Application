package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that extends the AbstractDAO class, Creates resultsets regarding the have table.
 * @author emkar
 *
 */
public class HaveDAO extends AbstractDAO{

	private static final String TABLENAME = "have";
	
	/**
	 * Constructor.
	 * 
	 * @param conn
	 */
	public HaveDAO(Connection conn){
		super(TABLENAME, conn);
		
	}
	/**
	 * Method that returns a resultset with all information from the have table.
	 * 
	 * @return rs
	 */
	public ResultSet getTableDesc() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT * FROM " + this.getTablename() + " ORDER BY storeid DESC";				
		try{
			p = this.getConnection().prepareStatement(query);
			rs = p.executeQuery();
			return rs;
		}
		catch(SQLException e){
			throw e;
		}		
	}
	/**
	 * Method that inserts data into the have table.
	 * 
	 * @param storeid
	 * @param bookid
	 * @param stock
	 * @param price
	 * @throws SQLException
	 */
	public void insertInto(int storeid, int bookid, int stock, int price) throws SQLException{
		PreparedStatement p;
		String insertQuery = "INSERT INTO have(storeid, bookid, stock, price) VALUES (?, ?, ?, ?)";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setInt(1, storeid);
				p.setInt(2, bookid);
				p.setInt(3, stock);
				p.setInt(4, price);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}
	}
	/**
	 * Method that deletes a row at a specific index.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRowStore(int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "DELETE FROM have WHERE storeid = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setInt(1, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}
	}
	/**
	 * Method that deletes a row at a specific index.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRowBook(int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "DELETE FROM have WHERE bookid = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setInt(1, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}
	}
	
}
