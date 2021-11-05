package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import pl.edu.agh.school.persistence.ISerializablePersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {
    @Provides
    private ISerializablePersistenceManager providePersistenceManager(SerializablePersistenceManager manager){
        return manager;
    }

    @Provides
    @Named("teachers")
    private String provideTeachersStorageFileName(){
        return "teachers2.dat";
    }

    @Provides
    @Named("classes")
    private String provideClassesStorageFileName(){
        return "classes2.dat";
    }

}
