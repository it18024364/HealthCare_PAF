package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ambulance {
	
	public Connection connect(){
		
		Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremgnt?serverTimezone=UTC", "root", "");
	 
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}

	public String readItems(){
		String output = "";
		
	try{
		Connection con = connect();
		
	 if (con == null){
		 return "Error while connecting to the database for reading.";
	 }
	 
	 output = " <table border=\"1\"><tr><th>ID</th><th>Name</th><th>model</th>"
	 + "<th>phone</th>" + "<th>Update</th><th>Remove</th></tr>";
	 
	 String query = "select * from ambulance";
	 
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 
	 while (rs.next()){
		 String id = Integer.toString(rs.getInt("a_id"));
		 String name = rs.getString("a_name");
		 String model = rs.getString("a_vehicleModel");
		 String phone = rs.getString("a_phone");		 
		
		 output += "<tr><td>" + id + "</td>";
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + model + "</td>";
		 output += "<td>" + phone + "</td>";
		 		 
		 output += "<td><input name=\"btnUpdate\" "
		 + " type=\"button\" value=\"Update\"></td>"
		 + "<td><form method=\"post\" action=\"items.jsp\">"
		 + "<input name=\"btnRemove\" "
		 + " type=\"submit\" value=\"Remove\">"
		 + "<input name=\"itemID\" type=\"hidden\" "
		 + " value=\"" + id + "\">" + "</form></td></tr>";
		 }
	 
		 con.close();
		
		 output += "</table>";
		 }
		catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	public String addAmbulance(String name, String model, String phone) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into ambulance(a_id,a_name,a_vehicleModel,a_phone) values(?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, model);
			preparedStmt.setString(4, phone);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateAmbulance(String id, String name, String model, String phone) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE ambulance SET a_name=?,a_vehicleModel=?,a_phone=? WHERE a_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, name);
			preparedStmnt.setString(2, model);
			preparedStmnt.setString(3, phone);
			
			preparedStmnt.setInt(4, Integer.parseInt(id));
			
			preparedStmnt.execute();
			con.close();
			
			output="Updated Successfully";
		}catch(Exception e) {
			output="error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String removeAmbulance(String id){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		 
		 // create a prepared statement
		 String query = "delete from ambulance where a_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(id));
	
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
	catch (Exception e)
	 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	 }
	return output;
	}

}
