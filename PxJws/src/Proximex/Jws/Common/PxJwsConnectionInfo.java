package Proximex.Jws.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PxJwsConnectionInfo {

	private static PxJwsConnectionInfo ConnectionInfoInstance = null;	
	
	private Connection PxDbConnection = null;
	
	private PxJwsConnectionInfo()
	{
		Init();
	}
	
	private void Init()
	{
		try
		{
			Properties props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			String ConnURL = props.getProperty("pxdb.jdbc.url");
			String DbLogon = props.getProperty("jdbc.username");
			String DbPassword = props.getProperty("jdbc.password");
			Class.forName("pxdb.jdbc.driver").newInstance();
			PxDbConnection = DriverManager.getConnection(ConnURL, DbLogon, DbPassword);
		}
		catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} catch (ClassNotFoundException cnfex) {
			cnfex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static synchronized PxJwsConnectionInfo getInstance()
	{
		if(ConnectionInfoInstance == null)
		{
			ConnectionInfoInstance = new PxJwsConnectionInfo();
		}
		return ConnectionInfoInstance;
	}
	
	public Connection getPxDbConnection (){
    	return PxDbConnection;
           
    }
	
}
