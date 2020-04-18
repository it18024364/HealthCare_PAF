package DBconnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DB {
	
	// A common method to connect to the DB
				public Connection connect() {
					Connection con = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");

						// Provide the correct details: DBServer/DBName, username, password
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gaming?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
						
						//For testing
					       System.out.println("DBSuccessfully connected ");
					} catch (Exception e) {
						e.printStackTrace();
					}
					return con;
				}
	
	
	

}
