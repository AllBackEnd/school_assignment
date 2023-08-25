package allback.school_assignment.student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import allback.school_assignment.student.domain.Student;

public interface StudentRepository extends MongoRepository<Student, Long>{
    
}
