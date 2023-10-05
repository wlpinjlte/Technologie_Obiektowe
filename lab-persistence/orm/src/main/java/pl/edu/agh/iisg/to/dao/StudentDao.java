package pl.edu.agh.iisg.to.dao;

import java.util.*;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
        try {
            Student student = save(new Student(firstName, lastName, indexNumber));
            return Optional.of(student);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
        try {
            return currentSession().createQuery("SELECT s FROM Student s WHERE s.indexNumber = :indexNumber", Student.class)
                    .setParameter("indexNumber", indexNumber).uniqueResultOptional();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student student) {
        Map<Course,Float> report=new HashMap<>();
        for(Course course: student.courseSet()){
            float sumOfGrades=0;
            int  numberOfGrades=0;
            for(Grade grade:student.gradeSet()){
                if(grade.course()==course){
                    sumOfGrades+=grade.grade();
                    numberOfGrades+=1;
                }
            }
            System.out.println();
            float average=sumOfGrades/numberOfGrades;
            report.put(course,average);
        }
        return  report;
    }

}
