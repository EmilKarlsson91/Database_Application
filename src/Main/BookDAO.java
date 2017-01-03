package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class that extends the AbstractDAO class, creates resultsets with data regarding book table.
 * @author emkar
 *
 */
public class BookDAO extends AbstractDAO {

	private static final String TABLENAME = "book";
	/**
	 * Constructor
	 * 
	 * @param conn
	 */
	public BookDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	/**
	 * Method that inserts data into book table.
	 * 
	 * @param name
	 * @param authorId
	 * @throws SQLException
	 */
	public void insertInto(String name, int authorId) throws SQLException{
		PreparedStatement p;
		String insertQuery = "INSERT INTO book(name, authorId) VALUES (?, ?);";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setInt(2, authorId);
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
		String insertQuery = "DELETE FROM book WHERE id = ?";

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
	 * @param id
	 * @throws SQLException
	 */
	public void updateRow(String name, int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "UPDATE book SET name=? WHERE id = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setInt(2, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}	
	}
}
