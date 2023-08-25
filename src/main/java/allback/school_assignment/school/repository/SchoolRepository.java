package allback.school_assignment.school.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import allback.school_assignment.school.domain.School;

public interface SchoolRepository extends MongoRepository<School, Long> {
}
