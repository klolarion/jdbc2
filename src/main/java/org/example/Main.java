package org.example;

import org.example.model.Student;
import org.example.service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Student 생성
        // Student -> 모든 건 클래스다 혹은 클래스로 만들어진 객체인 인스턴스
        // model, DTO, pojo <- MVC
        // Student student = new Student(id, name, age, grade);
        // Student student = new Student(1, "김철수", 20, "1학년");
//		System.out.println(student.getId());
//		System.out.println(student.getName());
//		System.out.println(student.getAge());
//		System.out.println(student.getGrade());
//		student.setName("김철철");
//		System.out.println(student.getName());
        // Student 저장
        // 비즈니스 로직 - 데이터 접근 로직
        StudentService service = new StudentService();
        Random random = new Random();
        // random.nextInt()
        String[] name1 = {"김", "이", "박", "최", "서"};
        String[] name2 = {"서후", "여진", "마크", "쿠키", "민지"};
        for (int i = 0; i < 5; i++) {
            // Student student = new Student(1, "김철수", 20, "1학년");
            Student student = new Student(1,
                    name1[random.nextInt(name1.length)] + name2[random.nextInt(name2.length)],
                    random.nextInt(10) + 10,
                    (random.nextInt(6) + 1) + "학년");
            service.addStudent(student); // DB에 들어간다!
        }
        // 저장된 Student 모두 불러오기
        service.findAll();
        // 저장된 특정 Student 불러오기
        service.findOne(20);
        // 저장된 Student 검색 불러오기
        service.findByLastName("최");
        // 저장된 Student 정렬하여 불러오기
        service.findAndSortByAge(); // 오름차순
        service.findAndSortByAgeDesc(); // 내림차순
        // 특정한 Student 수정하기
        int randomNumber = random.nextInt(30);
        System.out.println("수정할 ID : " + randomNumber);
        System.out.println("수정 전");
        service.findOne(randomNumber);
        if (service.update(new Student(randomNumber,
                name1[random.nextInt(name1.length)] + name2[random.nextInt(name2.length)],
                random.nextInt(10) + 10,
                (random.nextInt(6) + 1) + "학년"))) {
            System.out.println("수정 후");
            service.findOne(randomNumber);
            // 특정한 Student 삭제하기
            service.remove(randomNumber); // 1개 select -> pk. 조건 만족 -> where.
            // insert -> id를 제외(포함하더라도 의미 없는) 나머지가 제대로 입력된 객체(DTO)
            // update -> DTO 그 자체. (id는 그대로인데 나머지 수정할 필드가 변경된...)
            // delete -> id만.
        };




    }
}