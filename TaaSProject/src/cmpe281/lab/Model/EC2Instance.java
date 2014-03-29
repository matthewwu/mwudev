package cmpe281.lab.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import cmpe281.lab.DAL.MySQLConnection;

public class EC2Instance {

	private int Id;
	private String Name;
	private String DNS;
	
	public String getDNS() {
		return DNS;
	}

	public void setDNS(String dNS) {
		DNS = dNS;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getInstanceId() {
		return InstanceId;
	}

	public void setInstanceId(String instanceId) {
		InstanceId = instanceId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
	private String Type;
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}	

	private String InstanceId;
	private String Status;
	
	public ArrayList<EC2Instance> GetFromDb()
	{
		ArrayList<EC2Instance> tempCollection = new ArrayList<EC2Instance>();
		Connection DBConnection = MySQLConnection.getInstance();
		try
		{				
		Statement sqlStatement = DBConnection.createStatement();
		String query = "select * from TaaSTestLabDb.EC2Instance";
		ResultSet resultset = sqlStatement.executeQuery(query);
		while (resultset.next()) 
		{
			EC2Instance temp = new EC2Instance();
			temp.Id = resultset.getInt("Id");
			temp.Name = resultset.getString("Name");
			temp.InstanceId = resultset.getString("InstanceId");			
			temp.Status = resultset.getString("Status");
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
