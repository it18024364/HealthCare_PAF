package model;

import DBconnection.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Appointment {
	
	DB DBobj = new DB();
	
			
			public String insertAppointment(String appPid,String appDname, String appPname, String appDate) {
				
				String output = "";
				
				try {
					Connection con = DBobj.connect();
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
