package pl.edu.agh.to.school.Student;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.Course.Course;
import pl.edu.agh.to.school.Course.CourseRepository;
import pl.edu.agh.to.school.Grade.Grade;
import pl.edu.agh.to.school.Grade.GradeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Student getStudentByIndexNumber(int indexNumber){
        return studentRepository.findByIndexNumber(indexNumber);
    }
    public boolean giveGrade(int courseId, int gradeValue, int studentId){
        Optional<Course> course=courseRepository.findById(courseId);
        Optional<Student> student=studentRepository.findById(studentId);
        if(!course.isPresent()||!student.isPresent()){
            return false;
        }
        Grade grade=new Grade(course.get(),student.get(),gradeValue);
        gradeRepository.save(grade);
        return true;
    }
    public OptionalDouble getAverage(int studentId){
        OptionalDouble avg=studentRepository.getAllGrade(studentId).stream().mapToDouble(d -> d).average();
        return avg;
    }
}