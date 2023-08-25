package allback.school_assignment.student.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void randomCreateStudentTest() {


        List<Student> studentList = new ArrayList<>();


        for (long i = 1; i < 11; i++) {

            studentList.add(Student.createRandomStudent(i,"2023"));

        }

        System.out.println(studentList);


    }

}