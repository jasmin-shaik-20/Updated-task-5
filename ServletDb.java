package com.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import com.Task4.EmployeeDetails;


@WebServlet("/Response1")
public class ServletDb extends HttpServlet 

{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");

		StringBuffer jb = new StringBuffer();

		String line = null;

		BufferedReader reader = request.getReader();

		while ((line = reader.readLine()) != null) {
		    jb.append(line);
		}
		JSONObject jsobj = new JSONObject(jb.toString());
		int empId = jsobj.getInt("empId");
		int emptype = jsobj.getInt("emptype");
		try
		{
			
			EmployeeDetails emp=new EmployeeDetails();
			String jsonData=emp.searchRecord(empId, emptype);
			response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        out.write(jsonData);
	        System.out.println(jsonData);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

