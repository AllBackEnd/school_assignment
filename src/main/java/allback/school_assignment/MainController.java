package allback.school_assignment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.exceptions.CsvValidationException;

import allback.school_assignment.school.service.SchoolService;
import allback.school_assignment.student.service.StudentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SchoolService schoolService;
    private final StudentService studentService;

    @GetMapping("/student")
    public String getData() {
        return "index";
    }

    @PostMapping("/student")
    public @ResponseBody String showStudents(@RequestParam("year") int year) throws CsvValidationException {
        String message = String.valueOf(year)+"년의 데이터가 이미 등록되어 있습니다.";
        if(!studentService.hasYearData(String.valueOf(year))) {
            schoolService.readCsvAndInsertData("school.csv", String.valueOf(year));
            studentService.randomCreateStudent(String.valueOf(year));
            message = String.valueOf(year)+"년 남자 학생 2420명, 여자 학생 2445명 생성 되었습니다.";
        }  
        return message;
    }
}

