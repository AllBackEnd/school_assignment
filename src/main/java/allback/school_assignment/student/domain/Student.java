package allback.school_assignment.student.domain;

import allback.school_assignment.valuetype.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Student {

    @Id
    @Field("STD_ID")
    @Setter
    private Long id;
    
    @Field("GND")
    @Setter
    private Gender gender;

    @Field("SCR")
    private Long score;

    @Field("PRE1")
    private String firstPreferSchool;

    @Field("PRE2")
    private String secondPreferSchool;

    @Field("PRE3")
    private String thirdPreferSchool;

    @Field("PRE4")
    private String fourthPreferSchool;

    @Field("PRE5")
    private String fifthPreferSchool;

    @Field("PRE6")
    private String sixthPreferSchool;

    @Field("PRE7")
    private String seventhPreferSchool;

    @Field("YEAR_INFO")
    private String year;

    private Student(Long id, Gender gender, String year) {
            this.id = Integer.valueOf(year)*1000 + id;
            this.gender = gender;
            this.score = createRandomScore(id);
            final List<String> randomPreferSchool = createRandomPreferSchool(id, gender);
            this.firstPreferSchool = randomPreferSchool.get(0);
            this.secondPreferSchool = randomPreferSchool.get(1);
            this.thirdPreferSchool = randomPreferSchool.get(2);
            this.fourthPreferSchool = randomPreferSchool.get(3);
            this.fifthPreferSchool = randomPreferSchool.get(4);
            this.sixthPreferSchool = randomPreferSchool.get(5);
            this.seventhPreferSchool = randomPreferSchool.get(6);
            this.year = year;
    }
    public static Student createRandomStudent(Long id, Gender gender, String year) {
            return new Student(id, gender, year);
    }

    private Long createRandomScore(Long id) {
        Random random = new Random(id);
        long min = 0;
        long max = 100;
        return random.nextLong(max - min +1) + min;
    }

    private List<String> createRandomPreferSchool(Long id, Gender gender) {

        List<String> canSchoolList = new ArrayList<>();

                canSchoolList.add("흥덕고");
                canSchoolList.add("봉명고");
                canSchoolList.add("사대부고");
                canSchoolList.add("대성고");
                canSchoolList.add("주성고");
                canSchoolList.add("금천고");

        if(gender == Gender.남) {
            canSchoolList.addAll(Arrays.asList(
                        "충북고"
                    , "청주고"
                    , "청석고"
                    , "운호고"
                    , "세광고"
                    , "신흥고"
                    ));
        } else if (gender == Gender.여) {
            canSchoolList.addAll(Arrays.asList(
                        "청주여고"
                    , "일신여고"
                    , "충북여고"
                    , "산남고"
                    , "청주중앙여고"));
        }
        Random rand = new Random(id);
        Collections.shuffle(canSchoolList, rand);
        return canSchoolList;
    }
}
