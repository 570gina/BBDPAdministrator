package com.bbdp.suggestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserSuggestionServer {
	public static ArrayList getPatientSuggestion(Connection conn, ArrayList list) {
		try {
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select * FROM patientsuggestion where solve = '0' ORDER BY dateTime DESC");
		    while (rs.next()) {
		    	list.add(rs.getString("patientID"));
				list.add(rs.getString("dateTime"));
				list.add(rs.getString("content"));
				list.add(rs.getString("solve"));
		    }
		    rs = st.executeQuery("select * FROM patientsuggestion where solve = '1' ORDER BY dateTime DESC");
		    while (rs.next()) {
		    	list.add(rs.getString("patientID"));
				list.add(rs.getString("dateTime"));
				list.add(rs.getString("content"));
				list.add(rs.getString("solve"));
		    }
			rs.close();
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return list;
	}
	public static ArrayList getDoctorSuggestion(Connection conn, ArrayList list) {
		try {
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select * FROM doctorsuggestion where solve = '0' ORDER BY dateTime DESC");
		    while (rs.next()) {
		    	list.add(rs.getString("doctorID"));
				list.add(rs.getString("dateTime"));
				list.add(rs.getString("content"));
				list.add(rs.getString("solve"));
		    }
		    rs = st.executeQuery("select * FROM doctorsuggestion where solve = '1' ORDER BY dateTime DESC");
		    while (rs.next()) {
		    	list.add(rs.getString("doctorID"));
				list.add(rs.getString("dateTime"));
				list.add(rs.getString("content"));
				list.add(rs.getString("solve"));
		    }
			rs.close();
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return list;
	}
	public static int completePatientSuggestion(Connection conn, String patientID, String time) {
		int returnInt = 0;
		try {
			Statement st = conn.createStatement();
			returnInt = st.executeUpdate("UPDATE patientsuggestion SET solve= '1' WHERE patientID = '"+patientID+"' and dateTime = '"+time+"'");
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return returnInt;
	}
	public static int completeDoctorSuggestion(Connection conn, String doctorID, String time) {
		int returnInt = 0;
		try {
			Statement st = conn.createStatement();
			returnInt = st.executeUpdate("UPDATE doctorsuggestion SET solve= '1' WHERE doctorID = '"+doctorID+"' and dateTime = '"+time+"'");
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return returnInt;
	}
	public static int deletePatientSuggestion(Connection conn, String patientID, String time) {
		int returnInt = 0;
		try {
			Statement st = conn.createStatement();
			returnInt = st.executeUpdate("DELETE FROM patientsuggestion where patientID='"+patientID+"'and dateTime = '"+time+"'");
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return returnInt;
	}
	public static int deleteDoctorSuggestion(Connection conn, String doctorID, String time) {
		int returnInt = 0;
		try {
			Statement st = conn.createStatement();
			returnInt = st.executeUpdate("DELETE FROM doctorsuggestion where doctorID='"+doctorID+"'and dateTime = '"+time+"'");
		    st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      if (conn!=null) try {conn.close();}catch (Exception ignore) {}
		}
		return returnInt;
	}
}
