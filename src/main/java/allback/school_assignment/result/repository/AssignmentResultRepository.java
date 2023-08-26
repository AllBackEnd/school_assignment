package allback.school_assignment.result.repository;

import allback.school_assignment.result.domain.AssignmentResult;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssignmentResultRepository extends MongoRepository<AssignmentResult, Long> {
}
