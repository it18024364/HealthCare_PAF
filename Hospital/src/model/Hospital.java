package model;

import java.sql.*;


public class Hospital

	{ //A common method to connect to the DB
	
	public Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitald?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	public String insertHospital(String hospitalid, String hospitalname, int hospitalphone, String hospitalemail, String hospitallocation)
	 {
	 String output = ""; 
	 
	 try
	 {
	 Connection con = connect();
	 
	 		if (con == null)
	 		{return "Error while connecting to the database for inserting."; }
	 		// create a prepared statement
	 		String query = " insert into hospitals   "
	 		+ "(`hospitalid`,`hospitalname`,`hospitalphone`,`hospitalemail`,`hospitallocation`)"
	 		+ " values (?, ?, ?, ?, ?)";
	 		PreparedStatement preparedStmt = con.prepareStatement(query);
	 		
	 		// binding values
	 		 preparedStmt.setString(1, hospitalid);
	 		 preparedStmt.setString(2, hospitalname);
	 		 preparedStmt.setInt(3, hospitalphone);
	 		 preparedStmt.setString(4, hospitalemail);
	 		 preparedStmt.setString(5, hospitallocation); 
	 		// execute the statement
	 		 preparedStmt.execute();
	 		 con.close();
	 		 output = "Inserted successfully";
	 		 } 
	 catch (Exception e)
	 {
	 output = "Error while inserting the hospital.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	 
	public String readHospitals()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Hospitalid</th><th>Hospitalname</th><th>Hospitalphone</th><th>Hospitalemail</th><th>Hospitallocation</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from hospitals";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String hospitalid = Integer.toString(rs.getInt("hospitalid"));
	 String hospitalname = rs.getString("hospitalname");
	 String hospitalphone = Integer.toString(rs.getInt("hospitalphone"));
	 String hospitalemail = rs.getString("hospitalemail");
	 String hospitallocation = rs.getString("hospitallocation");
	 // Add into the html table
	 output += "<tr><td>" + hospitalid + "</td>";
	 output += "<td>" + hospitalname + "</td>";
	 output += "<td>" + hospitalphone + "</td>";
	 output += "<td>" + hospitalemail + "</td>";
	 output += "<td>" + hospitallocation + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\""
	 + "value=\"Update\" class=\"btn btn-secondary\"></td>"
	 + "<td><form method=\"post\" action=\"hospital.jsp\">"
	 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\""
	 + "class=\"btn btn-danger\">"
	 + "<input name=\"hospitalid\" type=\"hidden\" value=\"" + hospitalid
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the hospitals.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updateHospital(String hospitalid, String hospitalname, int hospitalphone, String hospitalemail, String hospitallocation)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE hospitals SET hospitalname=?,hospitalphone=?,hospitalemail=?,hospitallocation=?"
	 + "WHERE hospitalid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 //preparedStmt.setString(1, hospitalid);
	 preparedStmt.setString(2, hospitalname);
	 preparedStmt.setInt(3, hospitalphone);
	 preparedStmt.setString(4, hospitalemail);
	 preparedStmt.setString(5, hospitallocation);
	 preparedStmt.setInt(6, Integer.parseInt(hospitalid));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteHospital(String hospitalid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from items where hospitalid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(hospitalid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the hospital.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
   }


