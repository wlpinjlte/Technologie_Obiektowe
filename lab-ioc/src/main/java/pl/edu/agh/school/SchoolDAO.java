package pl.edu.agh.school;

import java.util.Collections;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import pl.edu.agh.guice.SchoolModule;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.SerialzablePersistenceInterface;

public class SchoolDAO {
    @Inject
    public final Logger log;

    private final List<Teacher> teachers;

    private final List<SchoolClass> classes;
    @Inject
    private final SerialzablePersistenceInterface manager;
    @Inject
    public SchoolDAO(Logger log,SerialzablePersistenceInterface manager) {
        this.log=log;
        this.manager=manager;
        teachers = this.manager.loadTeachers();
        classes = this.manager.loadClasses();
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
