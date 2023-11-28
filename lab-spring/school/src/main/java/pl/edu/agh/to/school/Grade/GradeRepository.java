package pl.edu.agh.to.school.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.school.Student.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
