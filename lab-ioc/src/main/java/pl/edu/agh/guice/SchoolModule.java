package pl.edu.agh.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import pl.edu.agh.school.persistence.SerialzablePersistenceInterface;

//public class SchoolModule extends AbstractModule {
//    @Override
//    protected void configure() {
//        bind(String.class).annotatedWith(Names.named("Teacher Storage File Name"))
//                .toInstance("guice");
//    }
//    @Provides
//    SerialzablePersistenceInterface providePersistenceManager(JuiceSerializablePersistenceManager manager){
//        return manager;
//    }
//}
