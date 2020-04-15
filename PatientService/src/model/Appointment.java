package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Appointment {

	// A common method to connect to the DB
			private Connection connect() {
				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");

					// Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gaming?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
					
					//For testing
				       System.out.println("DBSuccessfully connected Appointment ");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}
			
	
			
			
			public String insertAppointment(String appPid,String appDname, String appPname, String appDate) {
				
				String output = "";
				
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for Appointment.";
					}
					// create a prepared statement
					System.out.println("inide innnsert");
				
					String query = "insert into appointment (`Aid`,`Pid`,`adocName`,`apatName`,`adate`)" + " values (?, ?, ?, ?, ?)";
					
				
			
					
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					//run up to here
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setInt(2, Integer.parseInt(appPid));
					preparedStmt.setString(3, appDname);
					preparedStmt.setString(4, appPname);
					preparedStmt.setString(5, appDate);
					
					
					
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Inserted successfully To Appointment";
					System.out.println("Inserted successfully To Appointment");
				} catch (Exception e) {
					output = "Error while inserting";
					System.err.println(e.getMessage());
				}
				return output;
			}			
			
	
	
}
