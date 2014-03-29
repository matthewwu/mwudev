package cmpe281.lab.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import cmpe281.lab.DAL.MySQLConnection;

public class TestLab {

	private int LabId;
	private String LabName;
	private Date LUTime;
	private int AdminUserId;
	private String AdminUserName;
	
	public String getAdminUserName() {
		return AdminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		AdminUserName = adminUserName;
	}
	public int getAdminUserId() {
		return AdminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		AdminUserId = adminUserId;
	}
	public int getLabId() {
		return LabId;
	}
	public void setLabId(int labId) {
		LabId = labId;
	}
	public String getLabName() {
		return LabName;
	}
	public void setLabName(String labName) {
		LabName = labName;
	}
	public Date getLUTime() {
		return LUTime;
	}
	public void setLUTime(Date lUTime) {
		LUTime = lUTime;
	}
	
	public ArrayList<TestLab> GetFromDb()
	{
		ArrayList<TestLab> tempCollection = new ArrayList<TestLab>();
		Connection DBConnection = MySQLConnection.getInstance();
		try
		{				
		
		Statement sqlStatement = DBConnection.createStatement();
		String query = "select TypeId,TypeName from TaaSTestLabDb.TestLab";
		ResultSet resultset = sqlStatement.executeQuery(query);
		while (resultset.next()) 
		{
			TestLab temp = new TestLab();
			temp.LabId = resultset.getInt("LabId");
			temp.LabName = resultset.getString("LabName");
			temp.LUTime = resultset.getDate("LUTime");
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
}
