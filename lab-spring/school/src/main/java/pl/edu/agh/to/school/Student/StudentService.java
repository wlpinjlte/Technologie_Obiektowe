package pl.edu.agh.to.school.Student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Student getStudentByIndexNumber(int indexNumber){
        return studentRepository.findByIndexNumber(indexNumber);
    }

}