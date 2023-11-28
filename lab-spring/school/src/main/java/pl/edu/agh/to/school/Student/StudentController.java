package pl.edu.agh.to.school.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<?> getStudentsOrStudent(@RequestParam(name = "indexNumber",required = false) String indexNumber) {
        if(indexNumber!=null){
            Student student = studentService.getStudentByIndexNumber(parseInt(indexNumber));
            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }
        }else{
            List<Student> students = studentService.getStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }
}
