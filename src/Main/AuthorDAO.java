package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDAO extends AbstractDAO {
	
	private static final String TABLENAME = "author";
	
	public AuthorDAO(Connection conn) {
		super(TABLENAME, conn);
	}

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
}
