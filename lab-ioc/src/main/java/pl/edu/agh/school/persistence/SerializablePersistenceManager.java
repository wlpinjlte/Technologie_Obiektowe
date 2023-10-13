package pl.edu.agh.school.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import
import javax.inject.Named;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import pl.edu.agh.guice.SchoolModule;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;

public final class SerializablePersistenceManager implements SerialzablePersistenceInterface{
    @Inject
    private final Logger log;
    @Inject
    @Named("Teacher Storage File Name")
    private final String teachersStorageFileName;

    @Inject
    @Named("Class Storage File Name")
    private final  String classStorageFileName;
    @Inject
    public SerializablePersistenceManager(String classStorageFileName,String teachersStorageFileName,Logger log) {
        this.teachersStorageFileName = teachersStorageFileName;
        this.classStorageFileName = classStorageFileName;
        this.log=log;
    }

    public SerializablePersistenceManager(String classStorageFileName, String teachersStorageFileName) {
        this.teachersStorageFileName=teachersStorageFileName;
        this.classStorageFileName=classStorageFileName;
        this.log=Logger.getInstance();
    }

    public void saveTeachers(List<Teacher> teachers) {
        log.log("saving Teachers");
        if (teachers == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
            oos.writeObject(teachers);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            log.log("There was an error while saving the teachers data", e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Teacher> loadTeachers() {
        log.log("loading Teachers");
        ArrayList<Teacher> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {

            res = (ArrayList<Teacher>) ios.readObject();
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            log.log("There was an error while loading the teachers data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }

    public void saveClasses(List<SchoolClass> classes) {
        log.log("saving Classes");
        if (classes == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {

            oos.writeObject(classes);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            log.log("There was an error while saving the classes data", e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SchoolClass> loadClasses() {
        log.log("loading Classes");
        ArrayList<SchoolClass> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
            res = (ArrayList<SchoolClass>) ios.readObject();
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            log.log("There was an error while loading the classes data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }
}
