package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentClient {
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_db" ,"root" ,"rockyrox"); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
		}



	public String insertPayment(String name, String email, String address, String contact_number, String card_name, String card_number, String expiry_date, String cvc_number) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into payment_details (payment_ID, name , email , address , contact_number , card_name , card_number , expiry_date , cvc_number)"
				 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, name); 
        preparedStmt.setString(3, email); 
        preparedStmt.setString(4, address); 
        preparedStmt.setString(5, contact_number); 
        preparedStmt.setString(6, card_name); 
        preparedStmt.setString(7, card_number); 
        preparedStmt.setString(8, expiry_date); 
        preparedStmt.setString(9, cvc_number); 
		
		System.out.println(name);
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newPayments = readPayments();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newPayments + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment details.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


	public String readPayments()
	{ 
			 String output = ""; 
			try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'>" 
			 +"<th> Name </th><th> Address </th>"
			 + "<th> Email </th><th>Contact Number</th>" 
			 + "<th>Card Name</th><th>Card Number</th>" 
			 + "<th>Expiration Date</th><th>CVC Number</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from payment_details"; 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			    String payment_ID = Integer.toString(rs.getInt("payment_ID")); 
			    String name = rs.getString("name");
				String address = rs.getString("email");
				String email = rs.getString("address");
				String contact_number = rs.getString("contact_number");
				String card_name = rs.getString("card_name");
				String card_number = rs.getString("card_number");
				String expiry_date = rs.getString("expiry_date");
				String cvc_number = rs.getString("cvc_number"); 
			 // Add a row into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + payment_ID + "'>"
					 + name + "</td>";
			 output += "<td>" + email + "</td>"; 
			 output += "<td>" + address + "</td>"; 
			 output += "<td>" + contact_number + "</td>"; 
			 output += "<td>" + card_name + "</td>";
			 output += "<td>" + card_number + "</td>"; 
			 output += "<td>" + expiry_date + "</td>"; 
			 output += "<td>" + cvc_number + "</td>"; 
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-itemid='" + payment_ID + "'></td>"
			 + "<td><form method='post' action='Item.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + payment_ID + "'>"
			 + "<input name='hidItemIDDelete' type='hidden' " 
			 + " value='" + payment_ID + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the payment details."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String deletePayment(String payment_ID)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from payment_details where payment_ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(payment_ID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPayments = readPayments();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newPayments + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment details.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}

	public String updatePayment(String payment_ID, String name, String email, String address, String contact_number, String card_name, String card_number, String expiry_date, String cvc_number)
	//4
	{
	String output = "";
	try {
	Connection conn = connect();
	if (conn == null) {
	return "Error while connecting to the database for updating.";
	}

	// create a prepared statement
	String query = "UPDATE payment_details SET name=?,email=?,address=?,contact_number=?,card_name=?,card_number=?,expiry_date=?,cvc_number=? WHERE payment_ID=?";
	PreparedStatement preparedStmt = conn.prepareStatement(query);
	//binding values
	preparedStmt.setString(1, name);
	preparedStmt.setString(2, email);
	preparedStmt.setString(3, address);
	preparedStmt.setString(4, contact_number);
	preparedStmt.setString(5, card_name);
	preparedStmt.setString(6, card_number);
	preparedStmt.setString(7, expiry_date);
	preparedStmt.setString(8, cvc_number);
	
	preparedStmt.setInt(9, Integer.parseInt(payment_ID));
	//execute the statement
	preparedStmt.execute();
	conn.close();
	String newPayments = readPayments();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newPayments + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the payment details.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


}
