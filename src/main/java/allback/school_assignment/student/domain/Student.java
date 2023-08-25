package allback.school_assignment.student.domain;

import allback.school_assignment.valuetype.Gender;
import allback.school_assignment.valuetype.GenderConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity(name = "STUDENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Student {

    @Id
    @Column(name = "STD_ID")
    private Long id;

    @Convert(converter = GenderConverter.class)
    @Column(name = "GND")
    private Gender gender;

    @Column(name = "SCR")
    private Long score;

    @Column(name = "PRE1")
    private String firstPreferSchool;

    @Column(name = "PRE2")
    private String secondPreferSchool;

    @Column(name = "PRE3")
    private String thirdPreferSchool;

    @Column(name = "PRE4")
    private String fourthPreferSchool;

    @Column(name = "PRE5")
    private String fifthPreferSchool;

    @Column(name = "PRE6")
    private String sixthPreferSchool;

    @Column(name = "PRE7")
    private String seventhPreferSchool;

    @Column(name = "YEAR_INFO")
    private String year;


    private Student(Long id, String year) {
        final Gender randomGender = Gender.values()[new Random().nextInt(Gender.values().length)];
        this.id = id;
        this.gender = randomGender;
        this.score = createRandomScore();
        final List<String> randomPreferSchool = createRandomPreferSchool(randomGender);
        this.firstPreferSchool = randomPreferSchool.get(0);
        this.secondPreferSchool = randomPreferSchool.get(1);
        this.thirdPreferSchool = randomPreferSchool.get(2);
        this.fourthPreferSchool = randomPreferSchool.get(3);
        this.fifthPreferSchool = randomPreferSchool.get(4);
        this.sixthPreferSchool = randomPreferSchool.get(5);
        this.seventhPreferSchool = randomPreferSchool.get(6);
        this.year = year;
    }

    public static Student createRandomStudent(Long id, String year) {

        return new Student(id, year);
    }

    private Long createRandomScore() {
        Random random = new Random();
        long min = 0;
        long max = 100;
        return random.nextLong(max - min +1) + min;
    }

    private List<String> createRandomPreferSchool(Gender gender) {

        List<String> canSchoolList = new ArrayList<>();

                canSchoolList.add("흥덕고");
                canSchoolList.add("봉명고");
                canSchoolList.add("사대부고");
                canSchoolList.add("대성고");
                canSchoolList.add("주성고");
                canSchoolList.add("금천고");

        if(gender == Gender.MALE) {
            canSchoolList.addAll(Arrays.asList(
                      "충북고"
                    , "청주고"
                    , "청석고"
                    , "운호고"
                    , "세광고"
                    , "신흥고"
                    ));
        } else if (gender == Gender.FEMALE) {
            canSchoolList.addAll(Arrays.asList(
                      "청주여고"
                    , "일신여고"
                    , "충북여고"
                    , "산남고"
                    , "청주중앙여고"));
        }

        Collections.shuffle(canSchoolList);
        return canSchoolList;
    }

}
