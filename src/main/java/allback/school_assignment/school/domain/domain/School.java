package allback.school_assignment.school.domain.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import allback.school_assignment.valuetype.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "SCHOOL")
public class School {

    @Id
    @Field("SCH_ID")
    private Long id;

    @Field("SCH_NAME")
    private String name;

    @Field("CAP")
    private Integer capacity;

    @Field("GND")
    private Gender acceptableGender;

    public School(long id, String name, int capacity, Gender acceptableGender) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.acceptableGender = acceptableGender;
    }

    public static School createSchool(Long id, String name, int capacity, Gender acceptableGender) {

        return new School(id, name, capacity, acceptableGender);
    }
}
