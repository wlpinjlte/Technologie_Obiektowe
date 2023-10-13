package pl.edu.agh.factories;

import com.google.inject.Inject;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Subject;

public class SubjectFactory {
    @Inject
    private final Logger logger;
    @Inject
    public SubjectFactory(Logger logger){
        this.logger=logger;
    }
    public Subject create(String name){
        return new Subject(name,logger);
    }
}
