package pl.edu.agh.to.school.Student;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.school.Course.CourseRepository;
import pl.edu.agh.to.school.Grade.Grade;
import pl.edu.agh.to.school.Grade.GradeRepository;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService, GradeRepository gradeRepository, CourseRepository courseRepository) {
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
    @Transactional
    @PostMapping(path="/{id}/givegrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giveGrade(@PathVariable("id") int studentId,@RequestParam Map<String, String> body){
        try{
            int courseId= parseInt(body.get("courseId"));
            int gradeValue= parseInt(body.get("gradeValue"));
            boolean success=studentService.giveGrade(courseId,gradeValue,studentId);
            if(success){
                return new ResponseEntity<>("Grade added succesfully!",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Student or Course not found", HttpStatus.NOT_FOUND);
            }
        }catch(NumberFormatException err){
            return new ResponseEntity<>("Wrong body request", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/{id}/average")
    public ResponseEntity<?> getAverage(@PathVariable("id") int studentId){
        if(studentService.getAverage(studentId).isEmpty()){
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(studentService.getAverage(studentId).getAsDouble(),HttpStatus.OK);
        }
    }
}
