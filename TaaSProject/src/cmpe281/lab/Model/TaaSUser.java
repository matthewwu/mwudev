package cmpe281.lab.Model;


public class TaaSUser
{
	private int UserId;
	private String UserName;
	private String Password;
	private String FullName;
	private int UserTypeId;
	private String UserTypeName;
		
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public int getUserTypeId() {
		return UserTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		UserTypeId = userTypeId;
	}
	public String getUserTypeName() {
		return UserTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		UserTypeName = userTypeName;
	}
	
}