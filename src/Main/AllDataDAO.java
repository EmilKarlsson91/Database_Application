package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class that extends the AbstractDAO class, creates a DAO with general information from all tables.
 * @author emkar
 * 
 */
public class AllDataDAO extends AbstractDAO{

	private static final String TABLENAME = "allBookData";
	/**
	 * Constructor.
	 * 
	 * @param conn
	 */
	public AllDataDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	/**
	 * Method that returns a resultset with storename, adress, bookname, authorname stock and price.
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet getAllData() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT store.name AS Store, store.adress AS Adress, book.name AS Book, author.name AS Author, stock AS Stock, price AS Price FROM have JOIN store ON storeid = store.id JOIN book ON bookid = book.id JOIN author ON book.authorid = author.id ORDER BY store.name DESC";
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
	 * Method that returns a resultset with the ids from store, book and author.
	 * 
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet getAllId() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT store.id, book.id, author.id FROM store JOIN book JOIN author";
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
	 * Method that returns a resultset with the ids from store and author.
	 * 
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet getStoreAndAuthorId() throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT store.id, author.id FROM store JOIN author";
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
	 * Method that returns a resultset with all elements containing the word you set as parameter.
	 * 
	 * @param statement
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet search(String statement) throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT store.name AS Store, book.name AS Book, author.name AS Author, stock AS Stock, price AS Price FROM have JOIN store ON store.id = storeid JOIN book ON book.id = bookid JOIN author ON author.id = authorid WHERE store.name LIKE ? OR book.name LIKE ? OR author.name LIKE ? OR stock LIKE ? OR price LIKE ? ORDER BY store.name DESC";
		try{
			statement = "%" + statement + "%";
			p = this.getConnection().prepareStatement(query);
			p.setString(1, statement);
			p.setString(2, statement);
			p.setString(3, statement);
			p.setString(4, statement);
			p.setString(5, statement);			
			rs = p.executeQuery();
			return rs;

		}
		catch(SQLException e){
			throw e;
		}
	}
}
