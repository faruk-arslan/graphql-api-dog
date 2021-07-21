package com.fa.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fa.graphql.entity.Dog;
import com.fa.graphql.exception.BreedNotFoundException;
import com.fa.graphql.exception.DogNotFoundException;
import com.fa.graphql.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository){
        this.dogRepository=dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean isDeleted=false;
        Iterable<Dog> allDogs=dogRepository.findAll();

        for (Dog d:allDogs) {
            if (d.getBreed().equals(breed)){
                dogRepository.delete(d);
                isDeleted=true;
            }
        }
        if(!isDeleted){
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return isDeleted;
    }

    public Dog updateDogName(String name, Long id){
        Optional<Dog> dog = dogRepository.findById(id);

        if (dog.isPresent()){
            Dog dogToChange=dog.get();

            dogToChange.setName(name);
            dogRepository.save(dogToChange);
            return dogToChange;
        }else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
