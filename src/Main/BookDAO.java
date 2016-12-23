package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO extends AbstractDAO {

	private static final String TABLENAME = "book";
	
	public BookDAO(Connection conn) {
		super(TABLENAME, conn);
	}
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
}
