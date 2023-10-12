package pl.edu.agh.school.persistence;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private PersistenceConfig configuration;
    private static Assembler assemblerIstance=null;
    private Assembler(PersistenceConfig configuration) {
        this.configuration = configuration;
    }

    public void setConfiguration(PersistenceConfig configuration) {
        this.configuration = configuration;
    }

    public static Assembler createAssembler(PersistenceConfig configuration) {
        if(assemblerIstance!=null){
            assemblerIstance.setConfiguration(configuration);
        }else{
            assemblerIstance=new Assembler(configuration);
        }
        return assemblerIstance;
    }

    public <T> T getInstance(Class<T> clazz) {
        // Tutaj możesz dodać kod do tworzenia instancji klasy na podstawie konfiguracji.
        // W tym przypadku użyjemy domyślnego konstruktora.
        if(clazz==SerializablePersistenceManager.class){
            try {
                return (T) new SerializablePersistenceManager(configuration.getClassStorageFileName(), configuration.getTeachersStorageFileName());
            } catch (Exception e) {
                throw new RuntimeException("Nie udało się utworzyć instancji klasy: " + clazz.getName(), e);
            }
        }
        return null;
    }
}
