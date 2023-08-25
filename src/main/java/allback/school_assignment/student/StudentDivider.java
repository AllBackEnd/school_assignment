package allback.school_assignment.student;


import allback.school_assignment.student.domain.Student;
import allback.school_assignment.valuetype.Gender;

import java.util.Comparator;
import java.util.List;


public class StudentDivider {

    public static List<Student> divideByGender(List<Student> studentList, Gender gender) {

        return studentList.stream().filter(s -> s.getGender().equals(gender)).toList();

    }

    public static List<Student> getTop10PercentGroup(List<Student> studentList) {

        return getGradeGroup(studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed()).toList(), 0, 0.1);
    }

    public static List<Student> getNext40PercentGroup(List<Student> studentList) {

        return getGradeGroup(studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed()).toList(), 0.1, 0.5);
    }

    public static List<Student> getFollowing40PercentGroup(List<Student> studentList) {

        return getGradeGroup(studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed()).toList(), 0.5, 0.9);
    }

    public static List<Student> getBottom40PercentGroup(List<Student> studentList) {

        return getGradeGroup(studentList.stream().sorted(Comparator.comparing(Student::getScore).reversed()).toList(), 0.9, 1.0);
    }

    private static List<Student> getGradeGroup(List<Student> students, double startPercentage, double endPercentage) {
        int startIndex = (int) (students.size() * startPercentage);
        int endIndex = (int) (students.size() * endPercentage);

        return students.subList(startIndex, endIndex);
    }

}
