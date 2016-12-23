package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreDAO extends AbstractDAO{
	

	
	private static final String TABLENAME = "store";
	
	public StoreDAO(Connection conn) {
		super(TABLENAME, conn);
	}
	
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
}
