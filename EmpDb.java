package com.emp;




import org.json.*;
import java.sql.*;

public class EmpDb {
	public static PreparedStatement insertEmployeeStmt1;
	public static PreparedStatement insertEmployeeStmt2;
	public static PreparedStatement insertEmployeeStmt3;

	public static PreparedStatement deleteEmployeeStmt1;
	public static PreparedStatement deleteEmployeeStmt2;
	public static PreparedStatement deleteEmployeeStmt3;

	public static PreparedStatement searchEmployeeStmt1;
	public static PreparedStatement searchEmployeeStmt2;
	public static PreparedStatement searchEmployeeStmt3;

	public static PreparedStatement listEmployeeStmt1;
	public static PreparedStatement listEmployeeStmt2;
	public static PreparedStatement listEmployeeStmt3;

	public EmpDb() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	public String deleteEmp(int empId, int emptype, Connection conn) throws SQLException, ClassNotFoundException {

		String jsonData = "1";
		try {

			if (emptype == 1) {
				deleteEmployeeStmt1 = conn.prepareStatement("DELETE FROM PermanentEmployee WHERE empId = ?");
				deleteEmployeeStmt1.setInt(1, empId);
				int ans = deleteEmployeeStmt1.executeUpdate();
				JSONArray jsonArray = new JSONArray();

				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee deleted");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				} else {
					jsonObject.put("status", "employee not found");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}

			} else if (emptype == 2) {
				deleteEmployeeStmt2 = conn.prepareStatement("DELETE FROM ContractEmployee WHERE empId = ?");
				deleteEmployeeStmt2.setInt(1, empId);
				int ans = deleteEmployeeStmt2.executeUpdate();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee deleted");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				} else {
					jsonObject.put("status", "employee not found");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}

			} else if (emptype == 3) {
				deleteEmployeeStmt3 = conn.prepareStatement("DELETE FROM PartTimeEmployee WHERE empId = ?");
				deleteEmployeeStmt3.setInt(1, empId);
				int ans = deleteEmployeeStmt3.executeUpdate();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee deleted");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				} else {
					jsonObject.put("status", "employee not found");
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}
			} else {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "emptype not found");
				jsonArray.put(jsonObject);
				jsonData = jsonArray.toString();
			}
		} finally {
			if (deleteEmployeeStmt1 != null) {
				deleteEmployeeStmt1.close();
			}

			if (deleteEmployeeStmt2 != null) {
				deleteEmployeeStmt2.close();
			}
			if (deleteEmployeeStmt3 != null) {
				deleteEmployeeStmt3.close();
			}
		}

		return jsonData;
	}

	public String searchRecord(int empId, int emptype, Connection conn) throws SQLException, ClassNotFoundException {
		String jsonData = "employee not found";
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		try {

			if (emptype == 1) {
				searchEmployeeStmt1 = conn
						.prepareStatement("SELECT empId, name, age, salary, bonus from PermanentEmployee WHERE empId = ?");

				searchEmployeeStmt1.setInt(1, empId);
				resultSet1 = searchEmployeeStmt1.executeQuery();
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
			} else if (emptype == 2) {
				searchEmployeeStmt2 = conn.prepareStatement(
						"SELECT empId, name, age, salary, contractDuration from ContractEmployee WHERE empId = ?");

				searchEmployeeStmt2.setInt(1, empId);
				resultSet2 = searchEmployeeStmt2.executeQuery();
				JSONArray jsonArray = new JSONArray();
				while (resultSet2.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("empId", resultSet2.getInt("empId"));
					jsonObject.put("name", resultSet2.getString("name"));
					jsonObject.put("age", resultSet2.getInt("age"));
					jsonObject.put("salary", resultSet2.getInt("salary"));
					jsonObject.put("contractDuration", resultSet2.getInt("contractDuration"));
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}
			} else if (emptype == 3) {
				searchEmployeeStmt3 = conn.prepareStatement(
						"SELECT empId, name, age, salary, hoursWorked from PartTimeEmployee WHERE empId = ?");

				searchEmployeeStmt3.setInt(1, empId);
				resultSet3 = searchEmployeeStmt3.executeQuery();
				JSONArray jsonArray = new JSONArray();
				while (resultSet3.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("empId", resultSet3.getInt("empId"));
					jsonObject.put("name", resultSet3.getString("name"));
					jsonObject.put("age", resultSet3.getInt("age"));
					jsonObject.put("salary", resultSet3.getInt("salary"));
					jsonObject.put("hoursWorked", resultSet3.getInt("hoursWorked"));
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}
			} else {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "emptype not found");
				jsonArray.put(jsonObject);
				jsonData = jsonArray.toString();
			}
		} finally {

			if (searchEmployeeStmt1 != null) {
				searchEmployeeStmt1.close();

			}
			if (searchEmployeeStmt2 != null) {

				searchEmployeeStmt2.close();

			}
			if (searchEmployeeStmt3 != null) {
				searchEmployeeStmt3.close();

			}
			if (resultSet1 != null) {
				resultSet1.close();

			}
			if (resultSet2 != null) {
				resultSet2.close();

			}
			if (resultSet3 != null) {
				resultSet3.close();

			}

		}

		return jsonData;
	}

	public String addEmployee(int empId,String name, int age, int salary, int fourthValue, int emptype, Connection conn)
			throws SQLException {

		String jsonData = "only you need to select 1,2,3";
		try {

			if (emptype == 1) {
				insertEmployeeStmt1 = conn.prepareStatement("INSERT INTO PermanentEmployee (empId,name, age, salary, bonus) VALUES (?, ?, ?, ?, ?)");
				int bonus = fourthValue;
				insertEmployeeStmt1.setInt(1, empId);
				insertEmployeeStmt1.setString(2, name);
				insertEmployeeStmt1.setInt(3, age);
				insertEmployeeStmt1.setInt(4, salary);
				insertEmployeeStmt1.setInt(5, bonus);
				int ans = insertEmployeeStmt1.executeUpdate();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee added");
					jsonArray.put(jsonObject);
				} else {
					jsonObject.put("status", "employee not added");

					jsonArray.put(jsonObject);
				}
				jsonData = jsonArray.toString();

			} else if (emptype == 2) {
				insertEmployeeStmt2 = conn.prepareStatement(
						"INSERT INTO ContractEmployee (empId,name, age, salary, contractDuration) VALUES (?, ?, ?, ?, ?)");
				int contractDuration = fourthValue;
				insertEmployeeStmt2.setInt(1, empId);
				insertEmployeeStmt2.setString(2, name);
				insertEmployeeStmt2.setInt(3, age);
				insertEmployeeStmt2.setInt(4, salary);
				insertEmployeeStmt2.setInt(5, contractDuration);
				int ans = insertEmployeeStmt2.executeUpdate();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee added");
					jsonArray.put(jsonObject);
				} else {
					jsonObject.put("status", "employee not added");

					jsonArray.put(jsonObject);
				}
				jsonData = jsonArray.toString();
			} else if (emptype == 3) {
				insertEmployeeStmt3 = conn.prepareStatement(
						"INSERT INTO PartTimeEmployee (empId,name, age, salary, hoursWorked) VALUES (?, ?, ?, ?, ?)");

				int hoursWorked = fourthValue;
				insertEmployeeStmt3.setInt(1, empId);
				insertEmployeeStmt3.setString(2, name);
				insertEmployeeStmt3.setInt(3, age);
				insertEmployeeStmt3.setInt(4, salary);
				insertEmployeeStmt3.setInt(5, hoursWorked);
				int ans = insertEmployeeStmt3.executeUpdate();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (ans > 0) {
					jsonObject.put("status", "employee added");
					jsonArray.put(jsonObject);
				} else {
					jsonObject.put("status", "employee not added");

					jsonArray.put(jsonObject);
				}
				jsonData = jsonArray.toString();
			} else {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "emptype not found");
				jsonArray.put(jsonObject);
				jsonData = jsonArray.toString();
			}
		} finally {
			if (insertEmployeeStmt1 != null) {
				insertEmployeeStmt1.close();
			}
			if (insertEmployeeStmt2 != null) {
				insertEmployeeStmt2.close();
			}
			if (insertEmployeeStmt3 != null) {
				insertEmployeeStmt3.close();
			}

		}
		return jsonData;
	}

	public String list(int emptype, Connection conn) throws SQLException, ClassNotFoundException {

		String jsonData = "employees not found";
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		try {
			if (emptype == 1) {
				listEmployeeStmt1 = conn.prepareStatement("SELECT * from PermanentEmployee");
				resultSet1 = listEmployeeStmt1.executeQuery();
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
			} else if (emptype == 2) {
				listEmployeeStmt2 = conn.prepareStatement("SELECT * from ContractEmployee");
				resultSet2 = listEmployeeStmt2.executeQuery();
				JSONArray jsonArray = new JSONArray();
				while (resultSet2.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("empId", resultSet2.getInt("empId"));
					jsonObject.put("name", resultSet2.getString("name"));
					jsonObject.put("age", resultSet2.getInt("age"));
					jsonObject.put("salary", resultSet2.getInt("salary"));
					jsonObject.put("contractDuration", resultSet2.getInt("contractDuration"));
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}
			} else if (emptype == 3) {
				listEmployeeStmt3 = conn.prepareStatement("SELECT * from PartTimeEmployee");
				resultSet3 = listEmployeeStmt3.executeQuery();
				JSONArray jsonArray = new JSONArray();
				while (resultSet3.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("empId", resultSet3.getInt("empId"));
					jsonObject.put("name", resultSet3.getString("name"));
					jsonObject.put("age", resultSet3.getInt("age"));
					jsonObject.put("salary", resultSet3.getInt("salary"));
					jsonObject.put("hoursWorked", resultSet3.getInt("hoursWorked"));
					jsonArray.put(jsonObject);
					jsonData = jsonArray.toString();
				}
			} else {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "emptype not found");
				jsonArray.put(jsonObject);
				jsonData = jsonArray.toString();
			}
		} finally {
			if (listEmployeeStmt1 != null) {
				listEmployeeStmt1.close();

			}
			if (listEmployeeStmt2 != null) {
				listEmployeeStmt2.close();

			}
			if (listEmployeeStmt3 != null) {
				listEmployeeStmt3.close();

			}

			if (resultSet1 != null) {
				resultSet1.close();

			}
			if (resultSet2 != null) {
				resultSet2.close();

			}
			if (resultSet3 != null) {
				resultSet3.close();

			}

		}

		return jsonData;
	}
}

