package allback.school_assignment.result.domain;

import allback.school_assignment.valuetype.Gender;
import allback.school_assignment.valuetype.GenderConverter;
import allback.school_assignment.valuetype.Grade;
import allback.school_assignment.valuetype.GradeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ASSIGNMENT_RESULT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResult {

    @Id
    @Column(name = "ASGN_ID")
    private Long id;

    @Column(name = "ASGN_RSN")
    private String assignedReason;

    @Convert(converter = GenderConverter.class)
    @Column(name = "GND")
    private Gender gender;

    @Convert(converter = GradeConverter.class)
    @Column(name = "GRAD")
    private Grade grade;

    @Column(name = "RND_NUM")
    private Integer randomNumber;

    @Column(name = "YEAR_INFO")
    private String year;
}
