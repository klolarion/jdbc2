package jdbc2.dao;

import org.example.model.Student;

import java.sql.SQLException;
import java.util.List;


public interface StudentDAO {

    void addStudent(Student student) throws SQLException;

    List<Student> findAllStudent() throws SQLException;

    Student findOneStudent(int id) throws SQLException;

    List<Student> findByLastName(String lastName) throws SQLException;

    List<Student> findAndSortByAge() throws SQLException;

    List<Student> findAndSortByAgeDesc() throws SQLException;

    void updateStudent(Student student) throws SQLException;

    void removeStudent(int id) throws SQLException;

}