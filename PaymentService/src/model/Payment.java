package model;

import java.sql.*;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String name, String number)
	{
		String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
	
		// create a prepared statement
		String query = " insert into payments(`cardHolderName`,`cardNumber`)"+ " values (?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
		// binding values
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, number);
	
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
	}
	catch (Exception e)
	{
		output = "Error while inserting the payment.";
		System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String readPayment()
	{
		String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
	
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>Payment ID</th><th>Card Holder Name</th><th>Card Number</th><th>Charge</th><th>Paid Date</th><th>Update</th><th>Remove</th></tr>";
		String query = "select * from payments";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
	
		// iterate through the rows in the result set
		while (rs.next())
		{
			String payID = Integer.toString(rs.getInt("payID"));
			String cardHolderName = rs.getString("cardHolderName");
			String cardNumber = rs.getString("cardNumber");
			String charge = Float.toString(rs.getFloat("charge"));
			Timestamp timestamp = rs.getTimestamp("paidDate"); 
			String paidDate = timestamp.toString();
	
			// Add into the html table
			output += "<tr><td>" + payID + "</td>";
			output += "<td>" + cardHolderName + "</td>";
			output += "<td>" + cardNumber + "</td>";
			output += "<td>" + charge + "</td>";
			output += "<td>" + paidDate + "</td>";
	
			// buttons
			output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"index.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"payID\" type=\"hidden\" value=\"" +payID+ "\">" + "</form></td></tr>";
		}
		con.close();
		
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
	{
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String updatePayment(String ID, String name, String number, String charge)
	{
		String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
	
		// create a prepared statement
		String query = "UPDATE payments SET cardHolderName=?,cardNumber=?,charge=?,paidDate=current_timestamp WHERE itemID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
		// binding values
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, number);
		preparedStmt.setFloat(3, Float.parseFloat(charge));
		preparedStmt.setInt(4, Integer.parseInt(ID));
	
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the payment.";
		System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String deletePayment(String payID)
	{
		String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting."; }
	
		// create a prepared statement
		String query = "delete from payments where payID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(payID));
	
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
	}
	catch (Exception e)
	{
		output = "Error while deleting the payment.";
		System.err.println(e.getMessage());
	}
	return output;
	}

}
