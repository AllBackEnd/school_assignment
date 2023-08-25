package allback.school_assignment.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import allback.school_assignment.student.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-std")
    public String getData() {
        // studentService.findAll();
        return "/Test";
    }

    @PostMapping("/make-std")
    public String makeStudentData(@RequestParam("year") String year) {
        studentService.randomCreateStudent(year);
        return "student"; 
    }

    @PostMapping("/show-students")
    public @ResponseBody String showStudents(@RequestParam("year") int year) {
        studentService.randomCreateStudent(String.valueOf(year));
        String studentInfoHtml = "<p>남자는 2420명, 여자는 2445명 생성 되었습니다.</p>";
        return studentInfoHtml;
    }
}

