package com.fa.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fa.graphql.entity.Dog;
import com.fa.graphql.exception.DogNotFoundException;
import com.fa.graphql.repository.DogRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog fndDogById(Long id){
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isPresent()){
            return dog.get();
        }
        else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
