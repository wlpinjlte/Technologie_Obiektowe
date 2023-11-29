package pl.edu.agh.to.school.Grade;

import jakarta.persistence.*;
import pl.edu.agh.to.school.Course.Course;
import pl.edu.agh.to.school.Student.Student;

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;
    private int gradeValue;
    public Grade(){}

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Grade(Course course, Student student, int gradeValue) {
        this.course = course;
        this.student = student;
        this.gradeValue = gradeValue;
    }

}
