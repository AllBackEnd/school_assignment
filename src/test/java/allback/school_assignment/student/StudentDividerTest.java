package allback.school_assignment.student;

import allback.school_assignment.student.domain.Student;
import allback.school_assignment.valuetype.Gender;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class StudentDividerTest {

    @Test
    @DisplayName("학생 성별에 따라 학생리스트를 따로 추출 할 수 있다.")
    void findByGenderTest() {

        // GIVEN
        List<Student> studentList = new ArrayList<>();

        for (long i = 1; i < 11; i++) {

            studentList.add(Student.createRandomStudent(i,"2023"));

        }

        // WHEN
        final List<Student> maleStudentList = StudentDivider.divideByGender(studentList, Gender.MALE);

        final List<Student> femaleStudentList = StudentDivider.divideByGender(studentList, Gender.FEMALE);


        // THEN

        Assertions.assertThat(maleStudentList).extracting(Student::getGender).containsOnly(Gender.MALE);
        Assertions.assertThat(femaleStudentList).extracting(Student::getGender).containsOnly(Gender.FEMALE);

        log.info(maleStudentList.toString());
        log.info(femaleStudentList.toString());
    }

    @Test
    @DisplayName("학생의 성적 순서에 따라 상위 ~ 하위 총 4그룹으로 나눌 수 있다.")
    void getGroupByScore() {

        // GIVEN
        List<Student> studentList = new ArrayList<>();

        for (long i = 1; i < 51; i++) {

            studentList.add(Student.createRandomStudent(i,"2023"));

        }

        // WHEN

        final List<Student> top10PercentGroup = StudentDivider.getTop10PercentGroup(studentList);
        final List<Student> next40PercentGroup = StudentDivider.getNext40PercentGroup(studentList);
        final List<Student> following40PercentGroup = StudentDivider.getFollowing40PercentGroup(studentList);
        final List<Student> bottom40PercentGroup = StudentDivider.getBottom40PercentGroup(studentList);

        // THEN

        Assertions.assertThat(studentList)
                .hasSize(top10PercentGroup.size()
                        + next40PercentGroup.size()
                        + following40PercentGroup.size()
                        + bottom40PercentGroup.size());

        Assertions.assertThat(next40PercentGroup.size()).isEqualTo(following40PercentGroup.size());

    }
}