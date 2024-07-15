package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
//		if (conn != null && conn.isClosed()) {
//			return conn;
//		}
//		conn = DriverManager.getConnection(URL, USER, PASSWORD);
//		return conn;
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("커넥션 연결");
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("커넥션 종료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
