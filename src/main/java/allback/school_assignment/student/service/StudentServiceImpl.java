package allback.school_assignment.student.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import allback.school_assignment.student.domain.Student;
import allback.school_assignment.student.repository.StudentRepository;
import allback.school_assignment.valuetype.Gender;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Boolean isYearData(String year){
        return studentRepository.findByYear(year).isEmpty();
    }

    @Transactional
    public void randomCreateStudent(String year) {
        Random random = new Random(Long.parseLong(year));
        for (long i = 1; i < 2421; i++) {
            studentRepository.save((Student.createRandomStudent(i, Gender.남, year, random)));
        }
        for (long i = 2421; i <4866;i++){
            studentRepository.save((Student.createRandomStudent(i, Gender.여, year, random)));
        }
    }
}
