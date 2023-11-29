package pl.edu.agh.to.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.to.school.Configurators.Configurator;
import pl.edu.agh.to.school.Course.Course;
import pl.edu.agh.to.school.Course.CourseRepository;
import pl.edu.agh.to.school.Course.CourseService;
import pl.edu.agh.to.school.Grade.Grade;
import pl.edu.agh.to.school.Grade.GradeRepository;
import pl.edu.agh.to.school.Student.Student;
import pl.edu.agh.to.school.Student.StudentController;
import pl.edu.agh.to.school.Student.StudentRepository;
import pl.edu.agh.to.school.Student.StudentService;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class SchoolApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	@Transactional
	public void getStudentTest(@Autowired StudentRepository studentRepository, @Autowired StudentService studentService){
		System.out.println(studentService.getStudents().size());
		//given
		Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "444444");
		studentRepository.save(kowalski);

		//then
		assertTrue(studentService.getStudentByIndexNumber(444444).equals(kowalski));
	}
	@Test
	@Transactional
	public void getStudentsListTest(@Autowired StudentRepository studentRepository, @Autowired StudentService studentService){
		//given
		Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "444444");
		Student kowalski1 = new Student("Mateusz", "Kowalski", LocalDate.now(), "444445");
		studentRepository.save(kowalski);
		studentRepository.save(kowalski1);

		//then
		assertTrue(studentService.getStudents().equals(studentRepository.findAll()));
	}
	@Test
	@Transactional
	public void giveGradeTest(@Autowired StudentRepository studentRepository, @Autowired StudentService studentService, @Autowired CourseRepository courseRepository,@Autowired GradeRepository gradeRepository){
		//given
		Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "444444");
		Course course=new Course("WDI");
		course.assignStudent(kowalski);
		studentRepository.save(kowalski);
		courseRepository.save(course);

		//result
		studentService.giveGrade(course.getId(),5,kowalski.getId());

		//then
		assertTrue(gradeRepository.findAll().stream().filter(grade -> grade.getStudent().equals(kowalski)).map(grade->grade.getGradeValue()).collect(Collectors.toList()).get(0)==5);
		assertTrue(gradeRepository.findAll().stream().filter(grade -> grade.getStudent().equals(kowalski)).map(grade->grade.getCourse()).collect(Collectors.toList()).get(0).equals(course));
	}

	@Test
	@Transactional
	public void getAverageTest(@Autowired StudentRepository studentRepository, @Autowired StudentService studentService, @Autowired CourseRepository courseRepository,@Autowired GradeRepository gradeRepository){
		//given
		Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "444444");
		Course course=new Course("WDI");
		Course course1=new Course("WDAI");
		course.assignStudent(kowalski);
		course1.assignStudent(kowalski);
		studentRepository.save(kowalski);
		courseRepository.save(course);
		courseRepository.save(course1);
		Grade grade1=new Grade(course,kowalski,3);
		Grade grade2=new Grade(course,kowalski,5);
		Grade grade3=new Grade(course1,kowalski,2);
		Grade grade4=new Grade(course1,kowalski,4);
		gradeRepository.save(grade1);
		gradeRepository.save(grade2);
		gradeRepository.save(grade3);
		gradeRepository.save(grade4);

		//then
		assertTrue(studentService.getAverage(kowalski.getId()).getAsDouble()==3.5);
	}

	@Test
	@Transactional
	public void getCoursesTest(@Autowired CourseRepository courseRepository,@Autowired CourseService courseService){
		//given
		Course course=new Course("WDI");
		Course course1=new Course("WDAI");
		courseRepository.save(course);
		courseRepository.save(course1);

		//then
		assertTrue(courseService.getCourses().equals(courseRepository.findAll()));
	}

	@Test
	@Transactional
	public void getCourseByIdTest(@Autowired CourseRepository courseRepository,@Autowired CourseService courseService){
		//given
		Course course=new Course("WDI");
		courseRepository.save(course);

		//then
		assertTrue(courseService.getCourseById(course.getId()).equals(courseRepository.findById(course.getId())));
	}

	@Test
	@Transactional
	public void getAverageFromCourseTest(@Autowired StudentRepository studentRepository, @Autowired CourseService courseService, @Autowired CourseRepository courseRepository,@Autowired GradeRepository gradeRepository){
		//given
		Course course=new Course("WDI");
		courseRepository.save(course);
		Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "444444");
		Student kowalski1 = new Student("Mateusz", "Kowalski", LocalDate.now(), "444445");
		studentRepository.save(kowalski);
		studentRepository.save(kowalski1);
		Grade grade1=new Grade(course,kowalski,2);
		Grade grade2=new Grade(course,kowalski1,4);
		Grade grade3=new Grade(course,kowalski,4);
		Grade grade4=new Grade(course,kowalski1,3);
		Grade grade5=new Grade(course,kowalski,5);
		gradeRepository.save(grade1);
		gradeRepository.save(grade2);
		gradeRepository.save(grade3);
		gradeRepository.save(grade4);
		gradeRepository.save(grade5);

		//then
		assertTrue(courseService.getAverageFromCourse(course.getId()).getAsDouble()==3.6);
	}
}
