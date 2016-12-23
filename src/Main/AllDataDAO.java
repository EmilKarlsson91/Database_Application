package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllDataDAO extends AbstractDAO{

	private static final String TABLENAME = "allBookData";
	
	public AllDataDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	
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
	public ResultSet search(String statement) throws SQLException{
		PreparedStatement p;
		ResultSet rs;
		String query = "SELECT store.name AS Store, book.name AS Book, author.name AS Author, stock AS Stock, price AS Price FROM have JOIN store ON store.id = storeid JOIN book ON book.id = bookid JOIN author ON author.id = authorid WHERE store.name LIKE ? OR book.name LIKE ? OR author.name LIKE ? OR stock LIKE ? OR price LIKE ? ORDER BY store.name DESC";
		try{
			statement = statement + "%";
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
