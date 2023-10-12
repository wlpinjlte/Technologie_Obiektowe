package pl.edu.agh.school.persistence;

import java.io.Serializable;
import java.util.List;

public interface SerialzablePersistenceInterface {
    public void save(List<Serializable> list);
    public List<Serializable> load();
}
