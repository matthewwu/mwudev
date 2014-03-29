package cmpe281.lab.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
	
	 private static Connection ConnectionInstance = null;
	 private static String ConnURL;
	 private static String DbLogon;
	 private static String DbPassword;
	 
	    private MySQLConnection() {
	    	
	    }

	    public static Connection getInstance() {

	    	try {
				// Create and load properties from MySQL config file
				Properties props = new Properties();
				props.load(Class.forName("cmpe281.lab.DAL.MySQLConnection").getClassLoader().getResourceAsStream("MySQL.properties"));
				
				// Hard code for now, put into config later
				//ConnURL = "jdbc:mysql://localhost/TaaSTestLabDb";
				//DbLogon = "root";
				//DbPassword = "";
				
				// Get informations from properties
				ConnURL = props.getProperty("url");
				DbLogon = props.getProperty("login");
				DbPassword = props.getProperty("password");
				
				// Create the connection to MySQL DB
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ConnectionInstance = DriverManager.getConnection(ConnURL, DbLogon, DbPassword);
	
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			} catch (ClassNotFoundException cnfex) {
				cnfex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    	
            return ConnectionInstance; 
	           
	    }
}
