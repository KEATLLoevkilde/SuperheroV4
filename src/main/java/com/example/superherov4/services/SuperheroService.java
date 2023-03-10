package com.example.superherov4.services;


import com.example.superherov4.models.Superhero;
import com.example.superherov4.repositories.SuperheroRepository;
import com.example.superherov4.repositories.SuperheroRepository_DB;
import dto.DTOCityName_HeroName;
import dto.DTOheroName_RealName_NumPowers;
import dto.DTOheroName_realName_Powers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroService {

//    //Stub
//    private SuperheroRepository repository;
//
//    public SuperheroService(SuperheroRepository repository) {
//        this.repository = repository;
//    }

    //DATABASE
    private SuperheroRepository_DB repository;

    public SuperheroService(SuperheroRepository_DB repository) {
        this.repository = repository;
    }

    public List<Superhero> getAllSuperheroes(){
        return repository.getSuperheroes();
    }

    public Superhero getSuperhero(String heroName){
        return repository.getSuperhero(heroName);
    }

    public DTOheroName_RealName_NumPowers getHeroNamesAndNumOfPowers(String heroName){
        return repository.getHeroNamesAndNumOfPowers(heroName);
    }

    public List<DTOheroName_RealName_NumPowers> getAllHeroNamesAndNumOfPowers(){
        return repository.getAllHeroNamesAndNumOfPowers();
    }

    public DTOheroName_realName_Powers getHeroNamesAndPowers(String heroName){
        return repository.getHeroNamesAndPowers(heroName);
    }
    public List<DTOheroName_realName_Powers> getAllHeroNamesAndPowers(){
        return repository.getAllHeroNamesAndPowers();
    }

    public DTOCityName_HeroName getCityNameAndHeroes(String cityName){
        return repository.getCityNameAndHeroes(cityName);
    }

    public List<DTOCityName_HeroName> getAllCityNamesAndHeroes(){
        return repository.getAllCityNamesAndHeroes();
    }



}
