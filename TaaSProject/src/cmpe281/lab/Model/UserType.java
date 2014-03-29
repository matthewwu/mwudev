package cmpe281.lab.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import cmpe281.lab.DAL.MySQLConnection;

public class UserType {

	private int TypeId;
	private String TypeName;
	public int getTypeId() {
		return TypeId;
	}
	public void setTypeId(int typeId) {
		TypeId = typeId;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	
	public ArrayList<UserType> GetFromDb()
	{
		ArrayList<UserType> tempCollection = new ArrayList<UserType>();
		Connection DBConnection = MySQLConnection.getInstance();
		try
		{				
		
		Statement sqlStatement = DBConnection.createStatement();
		String query = "select TypeId,TypeName from TaaSTestLabDb.UserType";
		ResultSet resultset = sqlStatement.executeQuery(query);
		while (resultset.next()) 
		{
			UserType temp = new UserType();
			temp.TypeId = resultset.getInt("TypeId");
			temp.TypeName = resultset.getString("TypeName");
			
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
