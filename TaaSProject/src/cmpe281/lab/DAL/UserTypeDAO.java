package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.UserType;

public class UserTypeDAO {	
	
	public List<UserType> getAll() {
		List<UserType> userTypeList = new ArrayList<UserType>();
		String sql = "select * from UserType";
		Connection conn = MySQLConnection.getInstance();
		if(conn!=null)
		{
			try {
				Statement state = conn.createStatement();
				ResultSet result = state.executeQuery(sql);
				while (result.next()) {
					UserType userType = new UserType();
					userType.setTypeId(result.getInt("TypeId"));
					userType.setTypeName(result.getString("TypeName"));
					userTypeList.add(userType);
				}
				
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
		return userTypeList;
	}
}
