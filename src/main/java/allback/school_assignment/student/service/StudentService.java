package allback.school_assignment.student.service;

import java.util.List;

import allback.school_assignment.student.domain.Student;

public interface StudentService {
    public Boolean hasYearData(String year);
    public List<Student> findAll();
    public void randomCreateStudent(String year);
    List<Student> findByYear(String year);
}
