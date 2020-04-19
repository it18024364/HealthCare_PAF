package com.javafiles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class items{
	//connection test method
	public Connection connect()

	{
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items","root","root");
		
			//ForTesting
			System.out.print("success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;

	}
	
	//method to insert
	public String insertItem(String code, String name, String price, String desc) 
	{		
		String output = "";
		try
		{ 
			Connection con = connect();
			if (con == null)
			{
				return"Error while connecting to the database";
			}
			// create a prepared statement
				String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, code);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4,price);
				preparedStmt.setString(5, desc); 
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
	//Read from DB method
	public String readitems()
	{
		String output="";
		try
		{
			Connection con=connect();
			if(con==null)
			{
				return "Error reading Db!";
			}	
			
			//preparing the html table
			output = "<table border=\"1\"><tr><th>Item Code</th>"
					+ "<th>Item Name</th>"
					+ "<th>Item Price</th>"
					+ "<th>item Description</th></tr>";
			String query= "select * from items";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterate through the rows in the resultset
			while(rs.next())
			{
				String itemID=Integer.toString(rs.getInt("itemID"));
				String itemCode= rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = rs.getString("itemPrice");
				String itemDesc = rs.getString("itemDesc"); 
				
				//Add into the table
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice+ "</td>";
				output += "<td>" + itemDesc + "</td>";
				
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\"></td>\r\n"
						+  "<td><form method=\"post\" action=\"index.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + itemID + "\">"
						+ "</form></td></tr>";
				
			
				}
			
			con.close();
			
			output += "</table>";
			}
			
		catch(Exception e)
		{
			output="Error while reading the items!";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Delete function
	public String deleteitems(String itemID)
	{
		String output="";
		try
		{
			Connection con = connect();
			if(con == null)
			{
				return "Error : connecting to Db to delete";
			}
			
			//creating a prepared statement to delete
			String query = "delete from items where itemID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values to id
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			
			//execute statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleto";
		}
		catch(Exception e)
		{
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

