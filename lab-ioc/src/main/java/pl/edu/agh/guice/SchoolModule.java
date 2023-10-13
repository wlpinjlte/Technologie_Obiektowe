package pl.edu.agh.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
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

        Multibinder<IMessageSerializer> serializerMultibinder = Multibinder.newSetBinder(binder(), IMessageSerializer.class);
        serializerMultibinder.addBinding().to(FileMessageSerializer.class);
        serializerMultibinder.addBinding().to(ConsoleMessageSerializer.class);
    }
    @Provides
    SerialzablePersistenceInterface providePersistenceManager(SerializablePersistenceManager manager){
        return manager;
    }
}
