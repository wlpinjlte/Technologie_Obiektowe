package pl.edu.agh.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;
import pl.edu.agh.school.persistence.SerialzablePersistenceInterface;

public class SchoolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("Class Storage File Name"))
                .toInstance("guice-classes.dat");
        bind(String.class).annotatedWith(Names.named("Teacher Storage File Name"))
                .toInstance("guice-teachers.dat");
        bind(String.class).annotatedWith(Names.named("filename"))
                .toInstance("persistence.log");
    }
    @Provides
    SerialzablePersistenceInterface providePersistenceManager(SerializablePersistenceManager manager){
        return manager;
    }
//    @Provides
//    FileMessageSerializer provideFileMessageSerializer(FileMessageSerializer serializer){
//        return serializer;
//    }
//    @Provides
//    @Singleton
//    Logger provideLogger(Logger logger){
//        return logger;
//    }
}
