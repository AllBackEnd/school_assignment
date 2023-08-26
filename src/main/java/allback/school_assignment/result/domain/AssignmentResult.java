package allback.school_assignment.result.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import allback.school_assignment.valuetype.Gender;
import allback.school_assignment.valuetype.Grade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResult {

    @Id
    @Field("ASGN_ID")
    private Long id;

    @Field("SCH_NAME")
    private String schName;

    @Field("ASGN_RSN")
    private String assignedReason;

    @Field("GND")
    private Gender gender;

    @Field("GRADE")
    private Grade grade;

    @Field("ALG")
    private String algorithm;

    @Field("RND_NUM")
    private Integer randomNumber;

    @Field("YEAR_INFO")
    private String year;

    public AssignmentResult(Long id, String schName, String assignedReason, Gender gender, Grade grade, String algorithm, Integer randomNumber, String year) {
        this.id = id;
        this.schName = schName;
        this.assignedReason = assignedReason;
        this.gender = gender;
        this.grade = grade;
        this.algorithm = algorithm;
        this.randomNumber = randomNumber;
        this.year = year;
    }

    public static AssignmentResult createAssignmentResult(Long id, String schName, String assignedReason, Gender gender, Grade grade, String algorithm, Integer randomNumber, String year) {
        return new AssignmentResult(id, schName, assignedReason, gender, grade, algorithm, randomNumber, year);
    }
}
