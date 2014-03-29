package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.Request;

public class RequestDAO {
	
	public void create(Request item) {
		String sql = "insert into TaaSTestLabDb.Request (ProjectId) values (?)";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);				
				prepare.setInt(1, item.getProjectId());								
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
	
	public void UpdateRequest(int RequestId, int Status,String EC2Instance)
	{
		String sql = "update TaaSTestLabDb.Request set Status = ?,EC2InstanceId=? where RequestId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);				
				prepare.setInt(1,Status);
				prepare.setString(2, EC2Instance);
				prepare.setInt(3, RequestId);
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
	
	public List<Request> getAll()
	{
		List<Request> tempCollection = new ArrayList<Request>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{						
				Statement sqlStatement = conn.createStatement();				
				String query = "select RequestId,R.ProjectId,P.Name,Status,P.Priority,EC2Instanceid,R.LUTime from TaaSTestLabDb.Request R  join TaaSTestLabDb.TestProject P on P.projectId = R.Projectid";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) 
				{
					Request temp = new Request();
					temp.setRequestId(resultset.getInt("RequestId"));
					temp.setProjectId(resultset.getInt("ProjectId"));
					temp.setProjectName(resultset.getString("Name"));							
					temp.setStatus(resultset.getInt("Status"));
					temp.setProjectPriority(resultset.getInt("Priority"));
					temp.setEC2InstanceId(resultset.getString("EC2InstanceId"));									
					temp.setRequestTime(resultset.getDate("LUTime"));
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
	
	public Request GetRequest(int rid)
	{
		Request temp = new Request();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{										
						
				String query = "select RequestId,R.ProjectId,P.Name,Status,P.Priority,EC2Instanceid,R.LUTime from TaaSTestLabDb.Request R  join TaaSTestLabDb.TestProject P on P.projectId = R.Projectid where R.RequestId = ?";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, rid);	
				ResultSet resultset = prepare.executeQuery();
				if (resultset.first()) 
				{					
					
					temp.setRequestId(resultset.getInt("RequestId"));
					temp.setProjectId(resultset.getInt("ProjectId"));
					temp.setProjectName(resultset.getString("Name"));							
					temp.setStatus(resultset.getInt("Status"));
					temp.setProjectPriority(resultset.getInt("Priority"));
					temp.setEC2InstanceId(resultset.getString("EC2InstanceId"));									
					temp.setRequestTime(resultset.getDate("LUTime"));
				
				}
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
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
		return temp;
	}

}
