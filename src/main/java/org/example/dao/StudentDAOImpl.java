package org.example.dao;

import org.example.model.Student;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements jdbc2.dao.StudentDAO {

    @Override // 덮어씌우기 -> 인터페이스에 모양만있는 메서드를 아래 메서드로 대체
    public void addStudent(Student student) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String query = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, student.getName());
        ps.setInt(2, student.getAge());
        ps.setString(3, student.getGrade());
        int result = ps.executeUpdate();
        if (result == 0)
            throw new SQLException("생성 실패");
        ps.close();
        DBUtil.closeConnection();
    }

    @Override
    public List<Student> findAllStudent() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String query = "SELECT * FROM students";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(
                        new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("grade")));
            }
            return students;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public Student findOneStudent(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String query = "SELECT * FROM students WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("grade"));
            } else {
                throw new SQLException("없는 ID");
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public List<Student> findByLastName(String lastName) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String query = "SELECT * FROM students WHERE name LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, lastName + "%"); // %나 _ 같은 건 setString할 때..
            rs = ps.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(
                        new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("grade")));
            }
            return students;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public List<Student> findAndSortByAge() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String query = "SELECT * FROM students ORDER BY age"; // DESC, (ASC)
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(
                        new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("grade")));
            }
            return students;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public List<Student> findAndSortByAgeDesc() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String query = "SELECT * FROM students ORDER BY age DESC"; // DESC, (ASC)
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(
                        new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("grade")));
            }
            return students;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getGrade());
            ps.setInt(4, student.getId());
            int result = ps.executeUpdate();
            if (result == 0)
                throw new SQLException("수정 실패");
        } finally {
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void removeStudent(int id) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "DELETE FROM students WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result == 0)
                throw new SQLException("삭제 실패");
        } finally {
            if (ps != null)
                ps.close();
            DBUtil.closeConnection();
        }
    }

}