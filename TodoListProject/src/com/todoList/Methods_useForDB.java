package com.todoList;

import java.sql.Connection;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Methods_useForDB {
	Statement st, st1, st2;
	ResultSet rs, rs1, rs2;
	PreparedStatement ps;
	static Connection conn;
	Scanner sc = new Scanner(System.in);

	public Methods_useForDB() throws SQLException {
		conn = DBconncection.getConnection();
		st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		st1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		st2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

	}

	public void checklogin(String email, String pass) throws SQLException {

		rs = st.executeQuery("select * from user ");
		int c = 0;
		while (rs.next()) {
			if (rs.getString(5).equals(email) && rs.getString(8).equals(pass)) {
				c = 1;
				break;
			} else if (email.equals("admin@gmail.com") && pass.equals("admin@123")) {
				c = 2;

				break;
			}
		}
		if (c == 1) {
			System.out.println("Successfully Login as user");
		} else if (c == 2) {
			System.out.println("\nSuccessfully Login as admin");
		} else {
			System.out.println("Please enter valid username and password");
		}
	}

	public void deleteTask(int id) throws SQLException {

		int i = st.executeUpdate("delete from task where userid=" + id);

		if (i == 1) {
			System.out.println("Successfully Deleted");
		} else {
			System.out.println("Sorry Not Deleted");
		}

	}

	public void editTask(int pid, String task, String descr) throws SQLException {

		ps = conn.prepareStatement("update task set taskName=?, description=? where taskid=?");
		ps.setString(1, task);
		ps.setString(2, descr);
		ps.setInt(3, pid);
		int n = ps.executeUpdate();

		if (n == 1) {
			System.out.println("Successfully Updated");
		} else {
			System.out.println("Sorry Not Updated");
		}

	}

	// select code first and press shift+alt and f

	
	
	public void TaskStatus(int tid) throws SQLException {
		rs = st.executeQuery("select status from task where taskid= " + tid);

		rs.next();
		System.out.println(rs.getString("status"));
		int status = 0;
		if (rs.getString("status").equals("pending")) {
			status = st.executeUpdate("update task set status='Completed' where taskid=" + tid);
			System.out.println("yeye");
		} 
		else {
			status = st.executeUpdate("update task set status='pending' where taskid=" + tid);
			System.out.println("haha");
		}

	}

	public void ChangeUserStatus(int uid) throws SQLException {
		rs = st.executeQuery("select status from user where userid= " + uid);

		rs.next();
		System.out.println(rs.getString("status"));
		int u_status = 0;
		if (rs.getString("status").equals("Activate")) {
			u_status = st.executeUpdate("update user set status='Deactivate' where userid=" + uid);
			System.out.println("yeye");
		} 
		else {
			u_status = st.executeUpdate("update user set status='Activate' where userid=" + uid);
			System.out.println("haha");
		}

	}

	public void userRegistration(int uid, String fname, String lname, String gender, String email, String mno, Date DOB,
			String Password, String Status) throws SQLException {
		String sql = "insert into user(userid,FirstName,LastName,Gender,email,mobile_no,DOB,password,status,registrationDate) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		{

			ps.setInt(1, uid);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, mno);
			ps.setDate(7, DOB);
			ps.setString(8, Password);
			ps.setString(9, "Activate");
			java.util.Date date = new java.util.Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);

			ps.setString(10, strDate);

		}

		int n = ps.executeUpdate();

		if (n == 1) {
			System.out.println("Successfully Added Elements");
		} else {
			System.out.println("Sorry Not Added");
		}

	}

	public void getAllUser() throws SQLException {
		rs = st.executeQuery("select * from user");
		System.out.println("userid" + "\t" + "FirstName" + "\t" + "LastName" + "\t" + "Gender" + "\t" + "email" + "\t\t"
				+ "MobileNo" + "\t" + "DOB" + "\t" + "Password" + "\t" + "Status" + "\t" + "Registration Date");
		while (rs.next()) {
			int id = rs.getInt("userid");
			String n = rs.getString(2);
			String d = rs.getString(3);
			String s = rs.getString(4);
			String e = rs.getString(5);
			String mn = rs.getString(6);
			Date dob = rs.getDate(7);
			String p = rs.getString(8);
			String status = rs.getString(9);
			Date rd = rs.getDate(10);

			System.out.println(id + "\t" + n + "\t" + d + "\t" + s + "\t" + e + "\t" + mn + "\t" + dob + "\t" + p + "\t"
					+ status + "\t" + rd + "\t");
		}

	}

	public void AddTask(int tid, String task, String descr, String Status, int uid) throws SQLException {

		String sql = "insert into task (Taskid,TaskName,description,status,userid,TaskDate) " 
		+ "values(?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		{

			ps.setInt(1, tid);
			ps.setString(2, task);
			ps.setString(3, descr);
			ps.setString(4, "pending");
			ps.setInt(5, uid);
			
			java.util.Date date = new java.util.Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			
			ps.setString(6, strDate);

		}
		int n = ps.executeUpdate();
		if (n == 1) {
			System.out.println("Task Added Successfully");
		} else {
			System.out.println("Sorry Task Not added");
		}

	}

	/*
	 * public void reportAccount(int uid) throws SQLException { int i =
	 * st.executeUpdate("delete from user where userid=" + uid);
	 * 
	 * if (i == 1) { System.out.println("Reporetd Successfully "); } else {
	 * System.out.println("Reporting not done successfully"); } }
	 */

	public static void main(String[] args) throws SQLException {
		Methods_useForDB m1 = new Methods_useForDB();
		// m1.checklogin("shree@gmail.com", "Shree@123");
		// m1.checklogin("admin@gmail.com", "admin@123");
		// m1.deleteTask(2);
		// m1.updateUserStatus(3);
		// m1.editTask(2, "running", "runing");
		// m1.UserLogin(1);/
		// m1.getAllUser();
		// m1.ActivateUserStatus(1);
		//m1.userRegistration(6, "Bharat", "Gayali", "Male", "bharat@gmail.com", "9097894765",
				//Date.valueOf("1965-06-10"), "bharat@123", "Activate");
		//m1.AddTask(2, "CodingSession", "TodoList", "AboutToComplete", 3);
		// m1.ChangeUserStatus(1);
		m1.TaskStatus(4);
	}
}


