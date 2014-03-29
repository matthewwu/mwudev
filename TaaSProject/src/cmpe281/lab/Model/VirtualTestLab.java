package cmpe281.lab.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import cmpe281.lab.DAL.MySQLConnection;

public class VirtualTestLab {
	
	private int VLabId;
	private String VLabName;
	private int ParentTestLabId;
	private int PMId;
	private Date LUTime;
	private String PMUserName;
	private String InstanceId;
	
	public String getInstanceId() {
		return InstanceId;
	}
	public void setInstanceId(String instanceId) {
		InstanceId = instanceId;
	}
	public String getPMUserName() {
		return PMUserName;
	}
	public void setPMUserName(String pMUserName) {
		PMUserName = pMUserName;
	}
	public int getVLabId() {
		return VLabId;
	}
	public void setVLabId(int vLabId) {
		VLabId = vLabId;
	}
	public String getVLabName() {
		return VLabName;
	}
	public void setVLabName(String vLabName) {
		VLabName = vLabName;
	}
	public int getParentTestLabId() {
		return ParentTestLabId;
	}
	public void setParentTestLabId(int parentTestLabId) {
		ParentTestLabId = parentTestLabId;
	}
	public int getPMId() {
		return PMId;
	}
	public void setPMId(int pMId) {
		PMId = pMId;
	}
	public Date getLUTime() {
		return LUTime;
	}
	public void setLUTime(Date lUTime) {
		LUTime = lUTime;
	}
	
	public ArrayList<VirtualTestLab> GetFromDb()
	{
		ArrayList<VirtualTestLab> tempCollection = new ArrayList<VirtualTestLab>();
		Connection DBConnection = MySQLConnection.getInstance();
		try
		{				
		
		Statement sqlStatement = DBConnection.createStatement();
		String query = "select * from TaaSTestLabDb.VirtualTestLab";
		ResultSet resultset = sqlStatement.executeQuery(query);
		while (resultset.next()) 
		{
			VirtualTestLab temp = new VirtualTestLab();
			temp.VLabId = resultset.getInt("VLabId");
			temp.VLabName = resultset.getString("VLabName");
			temp.ParentTestLabId = resultset.getInt("ParentTestLabId");
			temp.PMId = resultset.getInt("PMId");
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
	
	public List<VirtualTestLab> filterVirtualLabByTestLab(List<VirtualTestLab> VTestLab, int TestLabId)
	{
		List<VirtualTestLab> tempCollection = new ArrayList<VirtualTestLab>();
		try
		{						
			for (VirtualTestLab item : VTestLab)
			{
				if(item.getParentTestLabId() == TestLabId)
				{
					tempCollection.add(item);
				}
			}									
			
		} catch (Exception ex) {
			
		} 
		
		return tempCollection;
	}
}
