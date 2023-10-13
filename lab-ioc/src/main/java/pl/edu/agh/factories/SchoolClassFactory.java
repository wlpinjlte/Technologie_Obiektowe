package pl.edu.agh.factories;

import com.google.inject.Inject;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.SchoolClass;

public class SchoolClassFactory {
    @Inject
    private final Logger logger;
    @Inject
    public SchoolClassFactory(Logger logger){
        this.logger=logger;
    }
    public SchoolClass create(String name, String profile){
        return new SchoolClass(name,profile,logger);
    }
}
