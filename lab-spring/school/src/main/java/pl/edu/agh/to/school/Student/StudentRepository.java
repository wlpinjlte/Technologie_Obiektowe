package pl.edu.agh.to.school.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.indexNumber = :indexNumber")
    Student findByIndexNumber(@Param("indexNumber") int indexNumber);

    @Query("select avg(g.gradeValue) from Grade g join Student s on g.student=s join Course c on c=g.course where s.id=:studentId group by c.id")
    List<Double> getAllGrade(@Param("studentId")int studentId);
}