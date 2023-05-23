package com.Task4;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class EmployeeDetails {
	
	private static Connection conn;
	private static PreparedStatement searchEmployeeStmt1;
	private static PreparedStatement searchEmployeeStmt2;
	private static PreparedStatement searchEmployeeStmt3;
	public EmployeeDetails() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	public static void Connection()
    {
        try {
        	
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Jasmin@20"); 
            searchEmployeeStmt1 = conn.prepareStatement("SELECT empId, name, age, salary, bonus from PermanentEmployee WHERE empId = ?");
            searchEmployeeStmt2 = conn.prepareStatement("SELECT empId, name, age, salary, hoursWorked from PartTimeEmployee WHERE empId = ?");
            searchEmployeeStmt3 = conn.prepareStatement("SELECT empId, name, age, salary, contractDuration from ContractEmployee WHERE empId = ?");
        }
        catch(Exception e){
            System.out.println("something went wrong");
        }


    }
	 public String searchRecord(int empId,int emptype) throws SQLException
	    {
		   String jsonData="Employee not found";
		 try {
		 if(emptype==1) {
		
	            Connection();
	            searchEmployeeStmt1.setInt(1, empId);
	            ResultSet resultSet1 = searchEmployeeStmt1.executeQuery();
	            JSONArray jsonArray = new JSONArray();
	            while (resultSet1.next()) {
	                JSONObject jsonObject = new JSONObject();
	                jsonObject.put("empId", resultSet1.getInt("empId"));
	                jsonObject.put("name", resultSet1.getString("name"));
	                jsonObject.put("age", resultSet1.getInt("age"));
	                jsonObject.put("salary", resultSet1.getInt("salary"));
	                jsonObject.put("bonus", resultSet1.getInt("bonus"));
	                jsonArray.put(jsonObject);
	            	jsonData = jsonArray.toString();
	            }
	    }
		 else if(emptype==2) {
			        
			            Connection();
			
			            searchEmployeeStmt2.setInt(1, empId);
			            ResultSet resultSet2 = searchEmployeeStmt2.executeQuery();
			            JSONArray jsonArray = new JSONArray();
			            while (resultSet2.next()) {
			                JSONObject jsonObject = new JSONObject();
			                jsonObject.put("empId", resultSet2.getInt(1));
			                jsonObject.put("name", resultSet2.getString(2));
			                jsonObject.put("age", resultSet2.getInt(3));
			                jsonObject.put("salary", resultSet2.getInt(4));
			                jsonObject.put("hoursWorked", resultSet2.getInt(5));
			                jsonArray.put(jsonObject);
			                jsonData = jsonArray.toString();
			            }
	
		 }
		 else if(emptype==3)
		 {
			
		            Connection();
		       
		            searchEmployeeStmt3.setInt(1, empId);
		            ResultSet resultSet3 = searchEmployeeStmt3.executeQuery();
		            JSONArray jsonArray = new JSONArray();
		            while (resultSet3.next()) {
		                JSONObject jsonObject = new JSONObject();
		                jsonObject.put("empId", resultSet3.getInt("empId"));
		                jsonObject.put("name", resultSet3.getString("name"));
		                jsonObject.put("age", resultSet3.getInt("age"));
		                jsonObject.put("salary", resultSet3.getInt("salary"));
		                jsonObject.put("ContractDuration", resultSet3.getInt("contractDuration"));
		                jsonArray.put(jsonObject);
		                jsonData = jsonArray.toString();
		            }
		            
		 }
		 }finally {

				if (searchEmployeeStmt1 != null) {
					searchEmployeeStmt1.close();

				}
				if (searchEmployeeStmt2 != null) {

					searchEmployeeStmt2.close();

				}
				if (searchEmployeeStmt3 != null) {
					searchEmployeeStmt3.close();

				}
//				if (resultSet1 != null) {
//					resultSet1.close();
//
//				}
//				if (resultSet2 != null) {
//					resultSet2.close();
//
//				}
//				if (resultSet3 != null) {
//					resultSet3.close();
//
//				}
		 }
		 return jsonData;
		 }
	
	 
		 }
	 
	 


