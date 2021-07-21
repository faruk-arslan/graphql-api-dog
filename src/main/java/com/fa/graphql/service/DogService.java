package com.fa.graphql.service;

import com.fa.graphql.entity.Dog;

import java.util.List;

public interface DogService {
    List<Dog> retrieveAllDogs();
    List<String> retrieveBreeds();
    String retrieveBreedById(Long id);
    List<String> retrieveNames();
}
