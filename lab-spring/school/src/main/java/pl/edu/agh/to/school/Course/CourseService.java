package pl.edu.agh.to.school.Course;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    public Course getCourseById(int id){
        return courseRepository.getReferenceById(id);
    }
}
