package pl.edu.agh.school.persistence;

public class PersistenceConfig {
    private String storageFileName;
    public PersistenceConfig(String storageFileName){
        this.storageFileName=storageFileName;
    }

    public String getStorageFileName() {
        return storageFileName;
    }
}
