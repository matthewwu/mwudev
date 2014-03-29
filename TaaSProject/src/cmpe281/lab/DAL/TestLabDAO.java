package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.TestLab;

public class TestLabDAO {
	
	
	public void create(TestLab item) {
		String sql = "insert into TaaSTestLabDb.TestLab (LabName,AdminUserId) values (?, ?)";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, item.getLabName());
				prepare.setInt(2, item.getAdminUserId());								
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if (conn != null) {
					try {
						conn.close();			
					} catch (Exception e) {
					}
				}
			}
		}
	}
	
	public void delete(int id) {
		String sql = "delete from TaasTestLabDb.TestLab where LabId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setInt(1, id);
				prepare.executeUpdate();			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if (conn != null) {
					try {
						conn.close();			
					} catch (Exception e) {
					}
				}
			}
		}
	}
	
	public List<TestLab> getAll()
	{
		List<TestLab> tempCollection = new ArrayList<TestLab>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{						
				Statement sqlStatement = conn.createStatement();
				String query = "select LabId,LabName,AdminUserId,UserName from TaaSTestLabDb.TestLab L join TaaSTestLabDb.User U on U.UserId = L.AdminUserId";			
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) 
				{
					TestLab temp = new TestLab();
					temp.setLabId(resultset.getInt("LabId"));
					temp.setLabName(resultset.getString("LabName"));
					//temp.setLUTime(resultset.getDate("LUTime"));
					temp.setAdminUserId(resultset.getInt("AdminUserId"));
					temp.setAdminUserName(resultset.getString("UserName"));
					
					tempCollection.add(temp);
				}
				conn.close();
				
			} catch (Exception ex) {
				
			} 
			finally {
				if (conn != null) {
					try {
						conn.close();			
					} catch (Exception e) {
					}
				}
			}
		}
		return tempCollection;
	}

}
