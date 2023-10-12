package pl.edu.agh.school.persistence;

public class PersistenceConfig {
    private final String teachersStorageFileName;
    private final String classStorageFileName;
    public PersistenceConfig(){
        this.teachersStorageFileName="teachers.dat";
        this.classStorageFileName="classes.dat";
    }

    public String getTeachersStorageFileName() {
        return teachersStorageFileName;
    }

    public String getClassStorageFileName() {
        return classStorageFileName;
    }
}
