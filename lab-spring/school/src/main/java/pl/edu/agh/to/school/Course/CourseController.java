package pl.edu.agh.to.school.Course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.school.Student.Student;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping(path = "courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public List<Course> getCourses(){
        System.out.println(courseService.getCourses());
        return courseService.getCourses();
    }
    @GetMapping("/{subjectId}/students")
    public ResponseEntity<?> getStudentsBySubjectId(@PathVariable int subjectId){
        Optional<Course> course=courseService.getCourseById(subjectId);
        if(course.isEmpty()){
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(course.get().getStudentList(),HttpStatus.OK);
        }
    }
    @GetMapping("/{courseId}/average")
    public ResponseEntity<?> getAverageFromCourse(@PathVariable int courseId){
        OptionalDouble average=courseService.getAverageFromCourse(courseId);
        if(average.isEmpty()){
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(average.getAsDouble(),HttpStatus.OK);
        }
    }
}
