package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {
	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gaming?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				
				//For testing
			       System.out.println("DBSuccessfully connected for read values");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertPatient(String patfirst, String patlname, String patemail, String patage, String patmobile, String patpassword) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				System.out.println("inide innnsert");
			
				String query = "insert into user (`Uid`,`first`,`lname`,`email`,`age`,`mobile`,`password`)" + " values (?, ?, ?, ?, ?, ?, ?)";
				
			
				//String query = " values (?, ?, ?, ?, ?, ?, ?)";
				
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//run up to here
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, patfirst);
				preparedStmt.setString(3, patlname);
				preparedStmt.setString(4, patemail);
				preparedStmt.setInt(5, Integer.parseInt(patage));
				preparedStmt.setInt(6, Integer.parseInt(patmobile));
				preparedStmt.setString(7, patpassword);
				
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully To Patient";
				System.out.println("Inserted successfully To Patient");
			} catch (Exception e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}
			return output;
		}

		
		
		public String readPatient() {
			String output = "";
			try {
				
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>First</th><th>lname</th><th>email</th><th>age</th><th>mobile</th><th>password</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from user";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				
				
				while (rs.next()) {
					
					String Uid = Integer.toString(rs.getInt("Uid"));
					String first = rs.getString("first");
					String lname = rs.getString("lname");
					String email = rs.getString("email");
					String age = Integer.toString(rs.getInt("age"));
					String mobile = Integer.toString(rs.getInt("mobile"));
					String password = rs.getString("password");
					
					// Add into the html table
					output += "<tr><td>" + first + "</td>";
					output += "<td>" + lname + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + age + "</td>";
					output += "<td>" + mobile + "</td>";
					output += "<td>" + password + "</td>";
					
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"index.jsp\">"//patient
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
							+ "<input name=\"Uid\" type=\"hidden\" value=\"" + Uid + "\">" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
				
			} catch (Exception e) {
				output = "Error while reading the patients.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String updatePatient(String patUid, String patfirst, String patlname	, String patemail, String patage, String patmobile, String patpassword) {
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				
				
				// create a prepared statement
				
				String query = "UPDATE user SET first=?,lname=?,email=?,age=?,mobile=?,password=? WHERE Uid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, patfirst);
				preparedStmt.setString(2, patlname);
				preparedStmt.setString(3, patemail);
				preparedStmt.setString(4, patage);
				preparedStmt.setInt(5, Integer.parseInt(patmobile));
				preparedStmt.setString(6, patpassword);
				preparedStmt.setInt(7, Integer.parseInt(patUid));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
				System.out.println("Update Successfully");
				
			} catch (Exception e) {
				output = "Error while updating the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		
		
		public String deletePatient(String patUid) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				
				// create a prepared statement
				String query ="delete from user where Uid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(patUid));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
				System.out.println("Deleted successfully");
				
			} catch (Exception e) {
				output = "Error while deleting the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	}
