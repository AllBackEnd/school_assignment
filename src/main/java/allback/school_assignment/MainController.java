package allback.school_assignment;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import allback.school_assignment.school.domain.School;
import allback.school_assignment.school.domain.domain.SchoolDivider;
import allback.school_assignment.school.repository.SchoolRepository;
import allback.school_assignment.student.StudentDivider;
import allback.school_assignment.student.domain.Student;
import allback.school_assignment.valuetype.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.exceptions.CsvValidationException;

import allback.school_assignment.school.service.SchoolService;
import allback.school_assignment.student.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final SchoolService schoolService;
    private final StudentService studentService;
    private final SchoolRepository schoolRepository;

    @GetMapping("/student")
    public String getData() {
        return "index";
    }

    @PostMapping("/student")
    public @ResponseBody String showStudents(@RequestParam("year") int year) throws CsvValidationException {
        String message = String.valueOf(year) + "년의 데이터가 이미 등록되어 있습니다.";
        if (!studentService.hasYearData(String.valueOf(year))) {
            schoolService.readCsvAndInsertData("school.csv", String.valueOf(year));
            studentService.randomCreateStudent(String.valueOf(year));
            message = String.valueOf(year) + "년 남자 학생 2420명, 여자 학생 2445명 생성 되었습니다.";
        }
        return message;
    }


    @PostMapping("/Assignment")
    @ResponseBody
    public String assign(@RequestParam("year") int year,
                         @RequestParam("seed") int seed) {

        final List<School> schoolList = schoolRepository.findByYear(String.valueOf(year));

        final List<Student> studentList = studentService.findByYear(String.valueOf(year));

        final GaleShapleyAlgorithm galeShapleyAlgorithm = new GaleShapleyAlgorithm();

        // 교육감 선택 시드
        final Random seedRandom = new Random(seed);

        List<Gender> genders = new ArrayList<>(Arrays.asList(Gender.남, Gender.여));

        for (Gender gender : genders) {

            final List<Student> male = StudentDivider.divideByGender(studentList, gender);

            final int maleSize = male.size();

            List<Double> doubleList = new ArrayList<>(Arrays.asList(maleSize * 0.1, maleSize * 0.5, maleSize * 0.9, maleSize * 1.0));

            List<Double> rounedDouble = new ArrayList<>(Arrays.asList(0.0, Math.ceil(doubleList.get(0)),
                    Math.ceil(doubleList.get(1)),
                    Math.ceil(doubleList.get(2)),
                    Math.ceil(doubleList.get(3))
            ));

            List<Double> ratios = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                final double l = (double) (rounedDouble.get(i + 1) - rounedDouble.get(i)) / maleSize;
                ratios.add(l);
            }

            final List<Map<String, Integer>> partition = SchoolDivider.createPartition(schoolList, ratios, gender);

            final List<Student> sortedMale = male.stream().sorted(Comparator.comparing(Student::getScore).reversed()).toList();

            for (int i = 0; i < 4; i++) {
                final int start = rounedDouble.get(i).intValue();
                final int end = rounedDouble.get(i + 1).intValue();

                List<Student> students_ids = sortedMale.subList(start, end);

                Map<String, Integer> schools = partition.get(i);
                Map<Integer, List<String>> students = new HashMap<>();

                List<Integer> tempStudent_ids = students_ids.stream().map(s -> s.getId().intValue()).toList();

                ArrayList<Integer> student_ids = new ArrayList<>(tempStudent_ids);

                Collections.shuffle(student_ids, seedRandom);

                List<String> tempSchool_ids = schools.keySet().stream().toList();

                final ArrayList<String> school_ids = new ArrayList<>(tempSchool_ids);

                Collections.shuffle(school_ids, seedRandom);

                int n_wish = 7;

                Map<Integer, List<String>> forbidden = new HashMap<>();

                for (Student student : students_ids) {
                    students.put(student.getId().intValue(), new ArrayList<>(Arrays.asList(
                            student.getFirstPreferSchool(),
                            student.getSecondPreferSchool(),
                            student.getThirdPreferSchool(),
                            student.getFourthPreferSchool(),
                            student.getFifthPreferSchool(),
                            student.getSixthPreferSchool(),
                            student.getSeventhPreferSchool())));
                }

                galeShapleyAlgorithm.allocate(schools, students, n_wish, forbidden, school_ids, student_ids);
                galeShapleyAlgorithm.printResult();
            }
        }
        String message = "배정이 완료되었습니다.";
        if (studentService.findByYear(String.valueOf(year)).isEmpty()){
            message = "해당 연도의 학생이 없습니다.";
        } 
        return message;
    }
}