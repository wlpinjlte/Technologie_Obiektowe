package pl.edu.agh.to.school.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.agh.to.school.Student.Student;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select avg(g.gradeValue) from Grade g join Course c on c=g.course where c.id=:courseId group by c.id")
    Double getAverageFromCourse(@Param("courseId")int courseId);
}
