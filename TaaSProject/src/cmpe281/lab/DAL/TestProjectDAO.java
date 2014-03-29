package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.EC2Instance;
import cmpe281.lab.Model.TaaSUser;
import cmpe281.lab.Model.TestProject;

public class TestProjectDAO {	
	
	public void create(TestProject item) {
		Connection conn = MySQLConnection.getInstance();
		String sql = "insert into TaaSTestLabDb.TestProject (Name,ParentVLabId,Priority) values (?, ?,?)";
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, item.getName());
				prepare.setInt(2, item.getParentVLabId());								
				prepare.setInt(3, item.getPriority());
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
		String sql = "delete from TaasTestLabDb.TestProject where ProjectId = ?";
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
	
	public List<TestProject> getAll()
	{
		List<TestProject> tempCollection = new ArrayList<TestProject>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{						
				Statement sqlStatement = conn.createStatement();
				String query = "select P.RequestStatus,P.ProjectId,P.Name,P.Priority,P.ParentVLabId,P.InstanceId from TaaSTestLabDb.TestProject P";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) 
				{
					TestProject temp = new TestProject();
					temp.setProjectId(resultset.getInt("ProjectId"));
					temp.setName(resultset.getString("Name"));								
					temp.setPriority(resultset.getInt("Priority"));
					temp.setParentVLabId(resultset.getInt( "ParentVLabId"));
					temp.setInstanceId(resultset.getString("Instanceid"));		
					temp.setRequestStatus(resultset.getInt("RequestStatus"));
					tempCollection.add(temp);
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
		return tempCollection;
	}
	
	public List<TaaSUser> getUsersByProject(int pid) {
		List<TaaSUser> tempCollection = new ArrayList<TaaSUser>();
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				String query = "SELECT U.UserId, U.UserName, U.Password, U.FullName, T.TypeName, P.ProjectId FROM TaaSTestLabDb.User U INNER JOIN TaaSTestLabDb.UserType T ON T.TypeId = U.UserTypeId INNER JOIN TaaSTestLabDb.TestProjectUserMap P ON P.UserId = U.UserId WHERE P.ProjectId = ?";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, pid);
				ResultSet rs = prepare.executeQuery();
				while (rs.next()) {
					TaaSUser user = new TaaSUser();
					user.setUserId(rs.getInt("UserId"));
					user.setUserName(rs.getString("UserName"));
					user.setPassword(rs.getString("Password"));
					user.setFullName(rs.getString("FullName"));
					user.setUserTypeName(rs.getString("TypeName"));
					tempCollection.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return tempCollection;
	}
	
	public void addUserToProject(int uid, int pid) {
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				String query = "INSERT INTO TaaSTestLabDb.TestProjectUserMap (ProjectId, UserId) VALUES (?, ?)";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, pid);
				prepare.setInt(2, uid);
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void removeUserToProject(int uid, int pid) {
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				String query = "DELETE FROM TaaSTestLabDb.TestProjectUserMap WHERE ProjectId = ? AND UserId = ?";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, pid);
				prepare.setInt(2, uid);
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public List<TestProject> getProjectByUser(int uid)
	{
		List<TestProject> tempCollection = new ArrayList<TestProject>();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{										
				String query = "select P.RequestStatus,P.ProjectId,P.Name,P.Priority,P.ParentVLabId,P.InstanceId from TaaSTestLabDb.TestProject P INNER JOIN TaaSTestLabDb.TestProjectUserMap M ON M.ProjectId = P.ProjectId WHERE M.UserId = ?";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, uid);	
				ResultSet resultset = prepare.executeQuery();
				while (resultset.next()) 
				{
					TestProject temp = new TestProject();
					temp.setProjectId(resultset.getInt("ProjectId"));
					temp.setName(resultset.getString("Name"));								
					temp.setPriority(resultset.getInt("Priority"));
					temp.setParentVLabId(resultset.getInt( "ParentVLabId"));
					temp.setInstanceId(resultset.getString("Instanceid"));					
					temp.setRequestStatus(resultset.getInt("RequestStatus"));
					
					if(temp.getInstanceId().length()>0)
					{
						EC2Instance tempInst = new EC2InstanceDAO().getById(temp.getInstanceId());
						if(tempInst!= null)
						{
							temp.setInstanceDNS(tempInst.getDNS());
						}
					}
					tempCollection.add(temp);
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
		return tempCollection;
	}

	public TestProject GetProject(int pid)
	{
		TestProject temp = new TestProject();
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try
			{										
				String query = "select P.RequestStatus,P.ProjectId,P.Name,P.Priority,P.ParentVLabId,P.InstanceId from TaaSTestLabDb.TestProject P where P.ProjectId = ?";
				PreparedStatement prepare = conn.prepareStatement(query);
				prepare.setInt(1, pid);	
				ResultSet resultset = prepare.executeQuery();
				if (resultset.first()) 
				{					
					temp.setProjectId(resultset.getInt("ProjectId"));
					temp.setName(resultset.getString("Name"));								
					temp.setPriority(resultset.getInt("Priority"));
					temp.setParentVLabId(resultset.getInt( "ParentVLabId"));
					temp.setInstanceId(resultset.getString("Instanceid"));
					temp.setRequestStatus(resultset.getInt("RequestStatus"));
				
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
	
	public void UpdateProject(int pId, String EC2Instance)
	{
		String sql = "update TaaSTestLabDb.TestProject set RequestStatus=2,InstanceId = ? where ProjectId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);								
				prepare.setString(1, EC2Instance);
				prepare.setInt(2, pId);
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
	
	public void UpdateProjectRequest(int pId, int Status)
	{
		String sql = "update TaaSTestLabDb.TestProject set RequestStatus = ? where ProjectId = ?";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				
				PreparedStatement prepare = conn.prepareStatement(sql);								
				prepare.setInt(1, Status);
				prepare.setInt(2, pId);
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
