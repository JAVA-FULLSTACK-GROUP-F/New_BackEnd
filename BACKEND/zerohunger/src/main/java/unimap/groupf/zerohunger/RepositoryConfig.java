package unimap.groupf.zerohunger;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import site.morn.boot.data.jpa.JpaRepositoryImpl;

@Configuration  //defines a Spring configuration class called RepositoryConfig
public class RepositoryConfig {

    @Autowired  //inject the EntityManager instance into the 
                //RepositoryConfig class, allowing it to access the underlying database.
    private EntityManager entityManager;

    @Bean   //provides CRUD operations for the Advice  nutrientAdviceRepository bean from RepositoryConfig can be injected into a service that handles nutrient advice-related operations.

    public JpaRepository<NutrientAdvice, Long> nutrientAdviceRepository() {
        return new JpaRepositoryImpl<>(NutrientAdvice.class, entityManager);
    }
}
/*
 The code defines a Spring configuration class called RepositoryConfig 
 that declares a bean named AdviceRepository of type JpaRepository<Advice, Long>. 
 This bean provides CRUD operations for the Advice entity, 
 which is not provided in the code snippet.

The @Autowired annotation is used to inject the EntityManager instance into the 
RepositoryConfig class, allowing it to access the underlying database.

The AdviceRepository method returns an instance of JpaRepositoryImpl<Advice, Long>, 
which is a concrete implementation of the JpaRepository interface. 
This implementation provides a set of standard CRUD methods for working 
with the Advice entity, such as save, delete, and findById.

By declaring the AdviceRepository bean in this way, 
it can be easily injected into other Spring-managed beans using the @Autowired annotation. 
For example, a service class that needs to perform CRUD operations on the Advice entity 
could declare a field of type JpaRepository<Advice, Long> 
and annotate it with @Autowired to have Spring inject an instance of AdviceRepository.
 */