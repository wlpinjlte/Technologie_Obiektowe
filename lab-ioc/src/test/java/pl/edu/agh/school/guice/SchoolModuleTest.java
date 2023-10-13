package pl.edu.agh.school.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import pl.edu.agh.guice.SchoolModule;
import pl.edu.agh.logger.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolModuleTest {
    @Test
    public void SingletonTest(){
        //given
        Injector injector= Guice.createInjector(new SchoolModule());
        Logger logger1=injector.getInstance(Logger.class);
        Logger logger2=injector.getInstance(Logger.class);

        //then
        assertTrue(logger1==logger2);
    }
}
