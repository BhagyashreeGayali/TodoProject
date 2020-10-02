package com.todoList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconncection {

	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/todo2";

	public static final String USER = "root";
	public static final String PASS = "root";
	static Connection conn = null;

	public static Connection getConnection() {
		/*
		 * try {
		 * 
		 * Class.forName("com.mysql.jdbc.Driver");
		 * System.out.println("Driver Loaded...");
		 * 
		 * System.out.println("Connecting to database..."); conn =
		 * DriverManager.getConnection( JDBC_URL, USER, PASS); } catch(Exception
		 * e) { e.printStackTrace(); } return conn;
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(" Welcome  to  TODO Application");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo2", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}
		return conn;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
