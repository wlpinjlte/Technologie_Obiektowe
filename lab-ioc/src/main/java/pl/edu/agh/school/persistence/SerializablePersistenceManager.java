package pl.edu.agh.school.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.logger.Logger;
public final class SerializablePersistenceManager implements SerialzablePersistenceInterface{

    private static final Logger log = Logger.getInstance();
    private String storageFileName;

    public SerializablePersistenceManager(String storageFileName) {
        this.storageFileName=storageFileName;
    }

    @Override
    public void save(List<Serializable> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageFileName))) {
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            log.log("There was an error while saving the classes data", e);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Serializable> load() {
        ArrayList<Serializable> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(storageFileName))) {
            res = (ArrayList<Serializable>) ios.readObject();
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
