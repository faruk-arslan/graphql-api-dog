package com.fa.graphql.service;

import com.fa.graphql.entity.Dog;
import com.fa.graphql.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService{
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> retrieveAllDogs(){
        return (List<Dog>) dogRepository.findAll();
    }

    public Optional retrieveDogById(long id){
        return dogRepository.findById(id);
    }

    public List<String> retrieveBreeds(){
        return dogRepository.findAllBreed();
    }

    public String retrieveBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        // If an id is requested that does not exist, use DogNotFoundException class to handle the error (404 not found).
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;

//        return dogRepository.findBreedById(id);
    }

    public List<String> retrieveNames(){
        return dogRepository.findAllName();
    }
}
