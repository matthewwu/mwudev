package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.EC2Instance;

public class EC2InstanceDAO {	
	
	public void create(EC2Instance item) {		

		String sql = "insert into TaaSTestLabDb.EC2Instance (Name,InstanceId,Status,Type,DNS) values (?, ?,?,?,?)";

		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {			
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, item.getName());
				prepare.setString(2, item.getInstanceId());
				prepare.setString(3, item.getStatus());				
				prepare.setString(4, item.getType());
				prepare.setString(5, item.getDNS());
				prepare.executeUpdate();			
				conn.close();
				
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
	
	public void delete(String InstanceId) {
		String sql = "delete from TaasTestLabDb.EC2Instance where InstanceId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn != null)
		{
			try {						
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, InstanceId);
				prepare.executeUpdate();					
				conn.close();
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
	
	public EC2Instance getById(String InstanceId) {
		EC2Instance temp = null;
		String sql = "SELECT * FROM TaaSTestLabDb.EC2Instance WHERE InstanceId = ?";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, InstanceId);
				ResultSet rs = prepare.executeQuery();
				if (rs.first()) {
					temp = new EC2Instance();
					temp.setId(rs.getInt("Id"));
					temp.setName(rs.getString("Name"));
					temp.setInstanceId(rs.getString("InstanceId"));					
					temp.setStatus(rs.getString("Status"));				
					temp.setDNS(rs.getString("DNS"));
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();			
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return temp;
	}
	
	public List<EC2Instance> getAll()
	{
		List<EC2Instance> tempCollection = new ArrayList<EC2Instance>();
		Connection conn = MySQLConnection.getInstance();
		if(conn != null)
		{
			try
			{									
				Statement sqlStatement = conn.createStatement();
				String query = "select * from TaaSTestLabDb.EC2Instance";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) 
				{
					EC2Instance temp = new EC2Instance();
					temp.setId(resultset.getInt("Id"));
					temp.setName(resultset.getString("Name"));

					temp.setInstanceId(resultset.getString("InstanceId"));					
					temp.setStatus(resultset.getString("Status"));
					temp.setType(resultset.getString("Type"));
					temp.setDNS(resultset.getString("DNS"));
					
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

	public List<EC2Instance> getAvailableInstance()
	{
		List<EC2Instance> tempCollection = new ArrayList<EC2Instance>();
		List<EC2Instance> allInstance = getAll();
		for(EC2Instance item:allInstance)
		{
			if(item.getDNS()!= null)
			{
				if(item.getDNS().length()>0)
				{
				tempCollection.add(item);
				}
			}
		}
				
		return tempCollection;
	}
	
	public void Update(EC2Instance instance)
	{
		String sql = "update TaaSTestLabDb.EC2Instance set Status = ?,DNS=?,Type=? where InstanceId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);				
				prepare.setString(1, instance.getStatus());
				prepare.setString(2, instance.getDNS());
				prepare.setString(3, instance.getType());
				prepare.setString(4, instance.getInstanceId());
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
	
	public void UpdateStatus(String InstanceId,int Status)
	{
		String sql = "update TaaSTestLabDb.EC2Instance set Status = ? where InstanceId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);				
				prepare.setInt(1, 1);
				prepare.setString(2, InstanceId);				
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
