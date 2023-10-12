package pl.edu.agh.school.persistence;

import java.io.Serializable;
import java.util.List;

public class ClassPersistenceManager implements SerialzablePersistenceInterface{
    private String classStorageFileName;
    public ClassPersistenceManager(){
        this.classStorageFileName="classes.dat";
    }
    @Override
    public void save(List<Serializable> list) {

    }

    @Override
    public List<Serializable> load() {
        return null;
    }
}
