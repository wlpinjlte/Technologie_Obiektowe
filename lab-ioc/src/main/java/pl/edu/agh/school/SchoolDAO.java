package pl.edu.agh.school;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.Assembler;
import pl.edu.agh.school.persistence.PersistenceConfig;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolDAO {

    public static final Logger log = Logger.getInstance();

    private final List<Teacher> teachers;

    private final List<SchoolClass> classes;

    private final SerializablePersistenceManager teacherManager;
    private final SerializablePersistenceManager classManager;

    public SchoolDAO() {
        PersistenceConfig teachersConfig=new PersistenceConfig("teachers.dat");
        PersistenceConfig classConfig=new PersistenceConfig("classes.dat");
        Assembler assembler=Assembler.createAssembler(teachersConfig);
        teacherManager = assembler.getInstance(SerializablePersistenceManager.class);
        teachers = (List<Teacher>) (List<?>)teacherManager.load();
        assembler.setConfiguration(classConfig);
        classManager = assembler.getInstance(SerializablePersistenceManager.class);
        classes = (List<SchoolClass>) (List<?>)classManager.load();
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            teacherManager.save((List<Serializable>) (List<?>)teachers);
            log.log("Added " + teacher.toString());
        }
    }

    public void addClass(SchoolClass newClass) {
        if (!classes.contains(newClass)) {
            classes.add(newClass);
            classManager.save((List<Serializable>) (List<?>)classes);
            log.log("Added " + newClass.toString());
        }
    }

    public List<SchoolClass> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public List<Teacher> getTeachers() {
        return Collections.unmodifiableList(teachers);
    }
}
