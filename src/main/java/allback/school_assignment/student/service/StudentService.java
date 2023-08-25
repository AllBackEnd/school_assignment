package allback.school_assignment.student.service;

import java.util.List;

import allback.school_assignment.student.domain.Student;

public interface StudentService {
    // public void makeData(int seed);
    public List<Student> findAll();
    public void randomCreateStudent(String year);
}
