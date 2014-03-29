package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cmpe281.lab.Model.TaaSUser;

public class TaaSUserDAO {

	public List<TaaSUser> getAllAdmin() {

		List<TaaSUser> tempCollection = new ArrayList<TaaSUser>();
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				Statement sqlStatement = conn.createStatement();
				String query = "select UserId,UserName,Password,FullName,UserTypeId,TypeName from TaaSTestLabDb.User U join TaaSTestLabDb.UserType T where U.UserTypeId = T.TypeId AND T.TypeName='SystemAdmin' ORDER BY U.UserId";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) {
					TaaSUser temp = new TaaSUser();
					temp.setUserId(resultset.getInt("UserId"));
					temp.setPassword(resultset.getString("Password"));
					temp.setUserName(resultset.getString("UserName"));
					temp.setFullName(resultset.getString("FullName"));
					temp.setUserTypeId(resultset.getInt("UserTypeId"));
					temp.setUserTypeName(resultset.getString("TypeName"));
					tempCollection.add(temp);
				}
				conn.close();

			} catch (Exception ex) {

			} finally {
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

	public List<TaaSUser> getAllPMs() {
		List<TaaSUser> tempCollection = new ArrayList<TaaSUser>();
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				Statement sqlStatement = conn.createStatement();
				String query = "select UserId,UserName,Password,FullName,UserTypeId,TypeName from TaaSTestLabDb.User U join TaaSTestLabDb.UserType T where U.UserTypeId = T.TypeId AND T.TypeName='ProjectManager' ORDER BY U.UserId";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) {
					TaaSUser temp = new TaaSUser();
					temp.setUserId(resultset.getInt("UserId"));
					temp.setPassword(resultset.getString("Password"));
					temp.setUserName(resultset.getString("UserName"));
					temp.setFullName(resultset.getString("FullName"));
					temp.setUserTypeId(resultset.getInt("UserTypeId"));
					temp.setUserTypeName(resultset.getString("TypeName"));
					tempCollection.add(temp);
				}
				conn.close();

			} catch (Exception ex) {

			} finally {
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

	public void create(TaaSUser user) {
		String sql = "insert into TaaSTestLabDb.User (UserName, Password, FullName, UserTypeId) values (?, ?, ?, ?)";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {

				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, user.getUserName());
				prepare.setString(2, user.getPassword());
				prepare.setString(3, user.getFullName());
				prepare.setInt(4, user.getUserTypeId());
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}
		}

	}

	public void update(TaaSUser user) {

		String sql = "UPDATE TaaSTestLabDb.User SET UserName=?, Password=?, FullName=?, UserTypeId=? WHERE UserId=?";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {

				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, user.getUserName());
				prepare.setString(2, user.getPassword());
				prepare.setString(3, user.getFullName());
				prepare.setInt(4, user.getUserTypeId());
				prepare.setInt(5, user.getUserId());
				prepare.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}
		}

	}
	
	public TaaSUser getUserByName(String UserName)
	{
		TaaSUser user = new TaaSUser();
		String sql = "select UserId,UserName,Password,FullName,UserTypeId,TypeName from TaaSTestLabDb.User U join TaaSTestLabDb.UserType T where U.UserTypeId = T.TypeId and U.UserName = ?";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, UserName);
				ResultSet result = prepare.executeQuery();
				if (result.first()) {
					user.setUserId(result.getInt("UserId"));
					user.setPassword(result.getString("Password"));
					user.setUserName(result.getString("UserName"));
					user.setFullName(result.getString("FullName"));
					user.setUserTypeId(result.getInt("UserTypeId"));
					user.setUserTypeName(result.getString("TypeName"));
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return user;
	}

	public TaaSUser get(int id) {
		TaaSUser user = new TaaSUser();
		String sql = "select UserId,UserName,Password,FullName,UserTypeId,TypeName from TaaSTestLabDb.User U join TaaSTestLabDb.UserType T where U.UserTypeId = T.TypeId and U.UserId = ?";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setInt(1, id);
				ResultSet result = prepare.executeQuery();
				if (result.first()) {
					user.setUserId(result.getInt("UserId"));
					user.setPassword(result.getString("Password"));
					user.setUserName(result.getString("UserName"));
					user.setFullName(result.getString("FullName"));
					user.setUserTypeId(result.getInt("UserTypeId"));
					user.setUserTypeName(result.getString("TypeName"));
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return user;
	}

	public void delete(int id) {
		String sql = "delete from TaasTestLabDb.User where UserId = ?";
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setInt(1, id);
				prepare.executeUpdate();
				/*
				 * Statement state = conn.createStatement(); String query =
				 * "delete from TaaSTestLabDb.User where UserId = " + id;
				 * state.executeUpdate(query);
				 */
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public List<TaaSUser> getAll() {
		List<TaaSUser> tempCollection = new ArrayList<TaaSUser>();
		Connection conn = MySQLConnection.getInstance();
		if (conn != null) {
			try {
				Statement sqlStatement = conn.createStatement();
				String query = "select UserId,UserName,Password,FullName,UserTypeId,TypeName from TaaSTestLabDb.User U join TaaSTestLabDb.UserType T where U.UserTypeId = T.TypeId ORDER BY U.UserId";
				ResultSet resultset = sqlStatement.executeQuery(query);
				while (resultset.next()) {
					TaaSUser temp = new TaaSUser();
					temp.setUserId(resultset.getInt("UserId"));
					temp.setPassword(resultset.getString("Password"));
					temp.setUserName(resultset.getString("UserName"));
					temp.setFullName(resultset.getString("FullName"));
					temp.setUserTypeId(resultset.getInt("UserTypeId"));
					temp.setUserTypeName(resultset.getString("TypeName"));
					tempCollection.add(temp);
				}
				conn.close();

			} catch (Exception ex) {

			} finally {
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
}
