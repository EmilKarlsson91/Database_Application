package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Class that extends AbstractDAO class, creates resultsets regarding author table.
 * 
 * @author Emil Karlsson
 *
 */
public class AuthorDAO extends AbstractDAO {
	
	private static final String TABLENAME = "author";
	
	/**
	 * Constructor
	 * 
	 * @param conn
	 */
	public AuthorDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	/**
	 * Method that inserts data into author table.
	 * @param name
	 * @param age
	 * @throws SQLException
	 */
	public void insertInto(String name, String age) throws SQLException{
		PreparedStatement p;
		String insertQuery = "INSERT INTO author(name, age) VALUES (?, ?);";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setString(2, age);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}
	}
	/**
	 * method that deletes a row on a specific index.
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRow(int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "DELETE FROM author WHERE id = ?";

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
	 * Method that updates a row on a specific index
	 * @param name
	 * @param age
	 * @param id
	 * @throws SQLException
	 */
	public void updateRow(String name, int age, int id) throws SQLException{
		PreparedStatement p;
		String insertQuery = "UPDATE author SET name=?, age=? WHERE id = ?";

			try{
				p = this.getConnection().prepareStatement(insertQuery);
				p.setString(1, name);
				p.setInt(2, age);
				p.setInt(3, id);
				p.executeUpdate();
			}
			catch(SQLException e){
				throw e;
			}	
	}
}
