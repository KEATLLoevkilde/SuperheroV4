package com.example.superherov4.controllers;
import com.example.superherov4.models.Superhero;
import com.example.superherov4.services.SuperheroService;
import dto.DTOCityName_HeroName;
import dto.DTOheroName_RealName_NumPowers;
import dto.DTOheroName_realName_Powers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "superhero")
public class SuperheroController {
    private SuperheroService superheroService;

    public SuperheroController(SuperheroService superheroService){
        this.superheroService = superheroService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Superhero>> allSuperheroes(){
        List <Superhero> superheroes = superheroService.getAllSuperheroes();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/{heroName}")
    public ResponseEntity<Superhero> getSuperhero(@PathVariable String heroName){
        Superhero superhero = superheroService.getSuperhero(heroName);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count/{heroName}")
    public ResponseEntity<DTOheroName_RealName_NumPowers> getHeroNamesAndNumOfPowers(@PathVariable String heroName){
        DTOheroName_RealName_NumPowers dto = superheroService.getHeroNamesAndNumOfPowers(heroName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count")
    public ResponseEntity<List<DTOheroName_RealName_NumPowers>> getAllHeroNamesAndNumOfPowers(){
        List<DTOheroName_RealName_NumPowers> dtoList = superheroService.getAllHeroNamesAndNumOfPowers();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping(path = "/superpower/{heroName}")
    public ResponseEntity<DTOheroName_realName_Powers>  getHeroNamesAndPowers(@PathVariable String heroName){
        DTOheroName_realName_Powers dto = superheroService.getHeroNamesAndPowers(heroName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower")
    public ResponseEntity<List<DTOheroName_realName_Powers>> getAllHeroNamesAndPowers(){
        List<DTOheroName_realName_Powers> dtoList = superheroService.getAllHeroNamesAndPowers();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/city/{cityName}")
    public ResponseEntity<DTOCityName_HeroName> getCityNameAndHeroes(@PathVariable String cityName){
        DTOCityName_HeroName dto = superheroService.getCityNameAndHeroes(cityName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path = "/city")
    public ResponseEntity<List<DTOCityName_HeroName>> getAllCityNamesAndHeroes(){
        List<DTOCityName_HeroName> dtoList = superheroService.getAllCityNamesAndHeroes();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }







}
