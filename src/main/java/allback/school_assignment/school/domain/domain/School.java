package allback.school_assignment.school.domain.domain;

import allback.school_assignment.valuetype.Gender;
import allback.school_assignment.valuetype.GenderConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "SCHOOL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class School {

    @Id
    @Column(name = "SCH_ID")
    private Long id;

    @Column(name = "SCH_NAME")
    private String name;

    @Column(name = "CAP")
    private Integer capacity;

    @Convert(converter = GenderConverter.class)
    @Column(name = "GND")
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
