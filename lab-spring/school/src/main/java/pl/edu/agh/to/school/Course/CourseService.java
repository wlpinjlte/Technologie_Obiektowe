package pl.edu.agh.to.school.Course;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    public Optional<Course> getCourseById(int id){
        return courseRepository.findById(id);
    }
    public OptionalDouble getAverageFromCourse(int id){
        return OptionalDouble.of(courseRepository.getAverageFromCourse(id));
    }
}
