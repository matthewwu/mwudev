package cmpe281.lab.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.DAL.MySQLConnection;

public class TestProject {

	private int ProjectId;
	private String Name;
	private int ParentVLabId;
	private int Priority;
	private String InstanceId;
	private String InstanceDNS;
	private int RequestStatus;
	
	public int getRequestStatus() {
		return RequestStatus;
	}


	public void setRequestStatus(int requestStatus) {
		RequestStatus = requestStatus;
	}


	public String getInstanceDNS() {
		return InstanceDNS;
	}


	public void setInstanceDNS(String instanceDNS) {
		InstanceDNS = instanceDNS;
	}

	private int ProjectuserId;	
	
	public int getProjectuserId() {
		return ProjectuserId;
	}


	public void setProjectuserId(int projectuserId) {
		ProjectuserId = projectuserId;
	}

	private String ProjectUser;
	
	public String getProjectUser() {
		return ProjectUser;
	}


	public void setProjectUser(String projectUser) {
		ProjectUser = projectUser;
	}


	public String getInstanceId() {
		return InstanceId;
	}


	public void setInstanceId(String instanceId) {
		InstanceId = instanceId;
	}


	public int getPriority() {
		return Priority;
	}


	public void setPriority(int priority) {
		Priority = priority;
	}


	public int getProjectId() {
		return ProjectId;
	}


	public void setProjectId(int projectId) {
		ProjectId = projectId;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getParentVLabId() {
		return ParentVLabId;
	}


	public void setParentVLabId(int parentVLabId) {
		ParentVLabId = parentVLabId;
	}


	public ArrayList<TestProject> GetFromDb()
	{
		ArrayList<TestProject> tempCollection = new ArrayList<TestProject>();
		Connection DBConnection = MySQLConnection.getInstance();
		try
		{				
		
		Statement sqlStatement = DBConnection.createStatement();
		String query = "select * from TaaSTestLabDb.TestProject";
		ResultSet resultset = sqlStatement.executeQuery(query);
		while (resultset.next()) 
		{
			TestProject temp = new TestProject();
			temp.ProjectId = resultset.getInt("ProjectId");
			temp.Name = resultset.getString("Name");
			temp.ParentVLabId = resultset.getInt("ParentVLabId");
			tempCollection.add(temp);
		}
			
		} catch (Exception ex) {
			
		} finally {
			if (DBConnection != null) {
				try {
					DBConnection.close();			
				} catch (Exception e) {
				}
			}
		}
		return tempCollection;
	}
	
	public List<TestProject> filterTestProjectByVLab(List<TestProject> Projects, int VLabId)
	{
		List<TestProject> tempCollection = new ArrayList<TestProject>();
		try
		{						
			for (TestProject item : Projects)
			{
				if(item.getParentVLabId() == VLabId)
				{
					tempCollection.add(item);
				}
			}					
			
			
		} catch (Exception ex) {
			
		} 
		
		return tempCollection;
	}
}
