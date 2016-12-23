package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HaveDAO extends AbstractDAO{

	private static final String TABLENAME = "have";
	
	public HaveDAO(Connection conn){
		super(TABLENAME, conn);
		
	}
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
