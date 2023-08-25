package allback.school_assignment.school.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import allback.school_assignment.valuetype.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Field("YEAR_INFO")
    private String year;

    public School(Long id, String name, int capacity, Gender acceptableGender, String year) {
        this.id = Integer.valueOf("1"+year)*10000 + id;
        this.name = name;
        this.capacity = capacity;
        this.acceptableGender = acceptableGender;
        this.year = year;
    }

    public static School createSchool(Long id, String name, int capacity, Gender acceptableGender, String year) {
        return new School(id, name, capacity, acceptableGender, year);
    }
}
