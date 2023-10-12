package pl.edu.agh.school;

import java.util.Collections;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.edu.agh.guice.SchoolModule;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.Assembler;
import pl.edu.agh.school.persistence.PersistenceConfig;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;
import pl.edu.agh.school.persistence.SerialzablePersistenceInterface;

public class SchoolDAO {

    public static final Logger log = Logger.getInstance();

    private final List<Teacher> teachers;

    private final List<SchoolClass> classes;

    private final SerialzablePersistenceInterface manager;

    public SchoolDAO() {
//        Assembler assembler=Assembler.createAssembler(new PersistenceConfig());
//        manager = assembler.getInstance(SerialzablePersistenceInterface.class);
        Injector injector= Guice.createInjector(new SchoolModule());
        manager=injector.getInstance(SerialzablePersistenceInterface.class);
        teachers = manager.loadTeachers();
        classes = manager.loadClasses();
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            manager.saveTeachers(teachers);
            log.log("Added " + teacher.toString());
        }
    }

    public void addClass(SchoolClass newClass) {
        if (!classes.contains(newClass)) {
            classes.add(newClass);
            manager.saveClasses(classes);
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
