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

    @Field("ASGN_RSN")
    private String assignedReason;

    @Field("GND")
    private Gender gender;

    @Field("GRAD")
    private Grade grade;

    @Field("RND_NUM")
    private Integer randomNumber;

    @Field("YEAR_INFO")
    private String year;
}
