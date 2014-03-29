package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.VirtualTestLab;

public class VirtualTestLabDAO {	
	
	public void create(VirtualTestLab item) {
		String sql = "insert into TaaSTestLabDb.VirtualTestLab (VLabName,ParentTestLabId,PMId,InstanceId) values (?, ?,?,?)";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, item.getVLabName());
				prepare.setInt(2, item.getParentTestLabId());
				prepare.setInt(3, item.getPMId());
				prepare.setString(4,item.getInstanceId());
				
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
		String sql = "delete from TaasTestLabDb.VirtualTestLab where VLabId = ?";
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
	
	public List<VirtualTestLab> getVirtualLabByTestLab(int TestLabId)
	{
		List<VirtualTestLab> tempCollection = new ArrayList<VirtualTestLab>();
		Connection conn = MySQLConnection.getInstance();
		try
		{						
			Statement sqlStatement = conn.createStatement();
			String query = "select * from TaaSTestLabDb.VirtualTestLab where ParentTestLabId = ?";
			PreparedStatement prepare = conn.prepareStatement(query);
			prepare.setInt(1, TestLabId);
			ResultSet resultset = sqlStatement.executeQuery(query);
			while (resultset.next()) 
			{
				VirtualTestLab temp = new VirtualTestLab();
				temp.setVLabId(resultset.getInt("VLabId"));
				temp.setVLabName(resultset.getString("VLabName"));				
				temp.setParentTestLabId(resultset.getInt("ParentTestLabId"));
				temp.setPMId(resultset.getInt("PMId"));
				
				tempCollection.add(temp);
			}
			
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
		return tempCollection;
	}
	
	
	
	public List<VirtualTestLab> getAll()
	{
		List<VirtualTestLab> tempCollection = new ArrayList<VirtualTestLab>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{						
				Statement sqlStatement = conn.createStatement();
				String query = "select VLabId,VLabName,LUTime,ParentTestLabId,PMId,UserName from TaaSTestLabDb.VirtualTestLab  L join TaaSTestLabDb.User U on U.UserId = L.PMId";			
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) 
				{
					VirtualTestLab temp = new VirtualTestLab();
					temp.setVLabId(resultset.getInt("VLabId"));
					temp.setVLabName(resultset.getString("VLabName"));
					temp.setLUTime(resultset.getDate("LUTime"));
					temp.setParentTestLabId(resultset.getInt("ParentTestLabId"));
					temp.setPMId(resultset.getInt("PMId"));
					temp.setPMUserName(resultset.getString("UserName"));
					
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
	
	public List<VirtualTestLab> filterVirtualLabByTestLab(List<VirtualTestLab> VTestLab, int TestLabId)
	{
		List<VirtualTestLab> tempCollection = new ArrayList<VirtualTestLab>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{						
				for (VirtualTestLab item : VTestLab)
				{
					if(item.getParentTestLabId() == TestLabId)
					{
						tempCollection.add(item);
					}
				}
				
				VirtualTestLab temp = new VirtualTestLab();
				
				tempCollection.add(temp);
				
				
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

	public void UpdateVLab(int VLabId,String EC2Instance)
	{
		String sql = "update TaaSTestLabDb.VirtualTestLab set InstanceId=? where VLabId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);				
				prepare.setInt(1,VLabId);
				prepare.setString(2, EC2Instance);				
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
	
}
