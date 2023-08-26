package allback.school_assignment.school.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import allback.school_assignment.school.domain.School;

public interface SchoolRepository extends MongoRepository<School, Long> {
    List<School> findByYear(String year);
}
