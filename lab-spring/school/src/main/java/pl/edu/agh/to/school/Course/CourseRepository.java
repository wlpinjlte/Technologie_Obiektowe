package pl.edu.agh.to.school.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.school.Student.Student;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
