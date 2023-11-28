package pl.edu.agh.to.school.Course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.school.Student.Student;

import java.util.List;

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
    @GetMapping("{subjectId}/students")
    public List<Student> getStudentsBySubjectId(@PathVariable int subjectId){
        return courseService.getCourseById(subjectId).getStudentList();
    }
}
