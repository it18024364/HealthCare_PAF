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
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertPatient(String Uid,String first, String lname, String email, String age, String mobile, String password) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				
				
				//String query = " insert into user (`Uid`,`first`,`lname`,`email`,`age`,`mobile`,`password`)"
					//	+ " values (?, ?, ?, ?, ?,?,?)";
				
				
				String query = " values (?, ?, ?, ?, ?,?,?)";
				
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, first);
				preparedStmt.setString(3, lname);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5, age);
				preparedStmt.setString(6, mobile);
				preparedStmt.setString(7, password);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting Patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readPatients() {
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
					String first = rs.getString("first");
					String lname = rs.getString("lname");
					String email = rs.getString("email");
					String age = rs.getString("age");
					String mobile = rs.getString("mobile");
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
							+ "<td><form method=\"post\" action=\"patients.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
							+ "<input name=\"email\" type=\"hidden\" value=\"" + email + "\">" + "</form></td></tr>";
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

		public String updatePatient(String first, String lname	, String email, String age, String mobile, String password) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE user SET first=?,lname=?,age=?,mobile=?,password=? WHERE email=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, first);
				preparedStmt.setString(2, lname);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, age);
				preparedStmt.setString(5, mobile);
				preparedStmt.setString(5, password);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deletePatient(String email) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from patients where email=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(email));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	}
