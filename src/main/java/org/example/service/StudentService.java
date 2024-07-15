package org.example.service;

import jdbc2.dao.StudentDAO;
import org.example.dao.StudentDAOImpl;
import org.example.model.Student;

import java.sql.SQLException;
import java.util.Random;

public class StudentService {
    StudentDAO dao; // StudentDAO interface -> impl -> class

    public StudentService() {
        // DAO를 하나 받아와서...
        dao = new StudentDAOImpl(); // 만들 때 생성됨
        // interface는 new로 생성할 수 없음.
        // Impl -> implements - 실제 클래스
    }

    public void addStudent(Student student) {
        try {
            dao.addStudent(student);
            System.out.println(student.getName() + " 추가 성공");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(student.getName() + " 추가 실패");
        }

    }

    public void findAll() {
        try {
            for (Student s : dao.findAllStudent()) {
                System.out.println(s.getId());
                System.out.println(s.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("학생을 조회할 수 없습니다");
        }
    }

    public void findOne(int id) {
        try {
            Student s = dao.findOneStudent(id);
            System.out.println(s.getName());
            System.out.println(s.getAge());
            System.out.println(s.getGrade());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("학생을 조회할 수 없습니다");
        }
    }

    public void findByLastName(String lastName) {
        try {
            for (Student s : dao.findByLastName(lastName)) {
                System.out.println(s.getId());
                System.out.println(s.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("학생을 조회할 수 없습니다");
        }
    }

    public void findAndSortByAge() {
        try {
            for (Student s : dao.findAndSortByAge()) {
                System.out.println(s.getId() + " " + s.getName() + " " + s.getAge());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("학생을 조회할 수 없습니다");
        }
    }

    public void findAndSortByAgeDesc() {
        try {
            for (Student s : dao.findAndSortByAgeDesc()) {
                System.out.println(s.getId() + " " + s.getName() + " " + s.getAge());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("학생을 조회할 수 없습니다");
        }
    }

    public boolean update(Student student) {
        try {
            dao.updateStudent(student);
            System.out.println(student.getName() + " 수정 성공");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(student.getName() + " 수정 실패");
            return false;
        }
    }

    public void remove(int id) {
        try {
            dao.removeStudent(id);
            System.out.println("삭제 성공");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("삭제 실패");
        }
    }

}