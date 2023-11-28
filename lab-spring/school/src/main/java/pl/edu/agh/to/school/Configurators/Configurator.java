package pl.edu.agh.to.school.Configurators;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.Course.Course;
import pl.edu.agh.to.school.Course.CourseRepository;
import pl.edu.agh.to.school.Grade.Grade;
import pl.edu.agh.to.school.Grade.GradeRepository;
import pl.edu.agh.to.school.Student.Student;
import pl.edu.agh.to.school.Student.StudentRepository;

import java.time.LocalDate;
@Configuration
public class Configurator{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, GradeRepository gradeRepository, CourseRepository courseRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "111111");
                Student kowalski1 = new Student("Mateusz", "Waga", LocalDate.now(), "222222");
                Student kowalski2 = new Student("Kasia", "Peciak", LocalDate.now(), "333333");
                studentRepository.save(kowalski);
                studentRepository.save(kowalski1);
                studentRepository.save(kowalski2);
            }
            if(courseRepository.count()==0){
                Course course1=new Course("Algebra");
                Course course2=new Course("Analiza");
                course1.assignStudent(studentRepository.findAll().get(0));
                course2.assignStudent(studentRepository.findAll().get(1));
                course2.assignStudent(studentRepository.findAll().get(2));
                courseRepository.save(course2);
                courseRepository.save(course1);
            }
            if(gradeRepository.count()==0){
                Grade grade1=new Grade(courseRepository.findAll().get(0), studentRepository.findAll().get(0),5);
                Grade grade2=new Grade(courseRepository.findAll().get(0), studentRepository.findAll().get(0),3);
                Grade grade3=new Grade(courseRepository.findAll().get(1), studentRepository.findAll().get(2),1);
                Grade grade4=new Grade(courseRepository.findAll().get(1), studentRepository.findAll().get(1),4);
                gradeRepository.save(grade1);
                gradeRepository.save(grade2);
                gradeRepository.save(grade3);
                gradeRepository.save(grade4);
            }

        };
    }
}
