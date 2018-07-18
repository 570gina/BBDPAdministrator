package com.bbdp.suggestion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;
import com.google.gson.Gson;

public class UserSuggestionServlet  extends HttpServlet {
	public UserSuggestionServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String state = request.getParameter("state");
        Gson gson = new Gson();
		DataSource datasource = (DataSource) getServletContext().getAttribute("db");
        Connection conn = null;
		try {
			conn = datasource.getConnection();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		switch(state) { 
			case "getPatientSuggestion":{
				ArrayList list = new ArrayList();
				response.getWriter().write(gson.toJson(UserSuggestionServer.getPatientSuggestion(conn,list)));
				break;
			}
			case "getDoctorSuggestion":{
				ArrayList list = new ArrayList();
				response.getWriter().write(gson.toJson(UserSuggestionServer.getDoctorSuggestion(conn,list)));
				break;
			}
			case "completePatientSuggestion":{
				String patientID = request.getParameter("patientID");
				String time = request.getParameter("time");
				response.getWriter().write(UserSuggestionServer.completePatientSuggestion(conn, patientID, time));
				break;
			}
			case "completeDoctorSuggestion":{
				String doctorID = request.getParameter("doctorID");
				String time = request.getParameter("time");
				response.getWriter().write(UserSuggestionServer.completeDoctorSuggestion(conn, doctorID, time));
				break;
			}	
			case "deletePatientSuggestion":{
				String patientID = request.getParameter("patientID");
				String time = request.getParameter("time");
				response.getWriter().write(UserSuggestionServer.deletePatientSuggestion(conn, patientID, time));
				break;
			}
			case "deleteDoctorSuggestion":{
				String doctorID = request.getParameter("doctorID");
				String time = request.getParameter("time");
				response.getWriter().write(UserSuggestionServer.deleteDoctorSuggestion(conn, doctorID, time));
				break;
			}	
			default:{
				if (conn!=null) try {conn.close();}catch (Exception ignore) {} 
				System.out.print("UserSuggestionServlet default");
			}
		}
	}
}
