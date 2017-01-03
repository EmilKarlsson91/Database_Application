package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class that extends the AbstractDAO class, creates resultsets regarding the store table.
 * 
 * @author Emil Karlsson.
 *
 */
public class StoreDAO extends AbstractDAO{
		
	private static final String TABLENAME = "store";
	/**
	 * Constructor.
	 * 
	 * @param conn
	 */
	public StoreDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	/**
	 * Method that inserts data into the store table.
	 * @param name
	 * @param adress
	 * @throws SQLException
	 */
	public void insertInto(String name, String adress) throws SQLException{
		PreparedStatement p;
		String insertQuery = "INSERT INTO store(name, adress) VALUES (?, ?);";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setString(2, adress);
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
	public void deleteRow(int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "DELETE FROM store WHERE id = ?";

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
	 * Method that updates a row at a specific index.
	 * 
	 * @param name
	 * @param adress
	 * @param id
	 * @throws SQLException
	 */
	public void updateRow(String name, String adress, int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "UPDATE store SET name=?, adress=? WHERE id = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setString(2, adress);
				p.setInt(3, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}	
	}
}
