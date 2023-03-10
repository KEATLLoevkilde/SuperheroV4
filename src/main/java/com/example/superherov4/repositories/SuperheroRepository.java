package com.example.superherov4.repositories;


import com.example.superherov4.models.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheroRepository {
    private List<Superhero> superheroes = new ArrayList<>();


    public SuperheroRepository() {
        superheroes.add(new Superhero("Superman", "Clark Kent", 1938));
        superheroes.add(new Superhero("Batman", "Bruce Wayne", 1939));
    }

    public List<Superhero> getSuperheroes() {
        return superheroes;
    }

    public Superhero getSuperhero(String heroName) {
        Superhero foundSuperhero = null;
        for (Superhero superhero : superheroes) {
            if (superhero.getHeroName().equalsIgnoreCase(heroName)) {
                foundSuperhero = superhero;
            }
        }
        return foundSuperhero;
    }

    public Superhero createSuperhero(Superhero newSuperhero) {
        superheroes.add(newSuperhero);
        return newSuperhero;
    }

    public Superhero editSuperhero(Superhero newSuperhero){
        Superhero superhero = getSuperhero(newSuperhero.getHeroName());
        int index = superheroes.indexOf(superhero);
        superheroes.set(index, newSuperhero);
        return superheroes.get(index);
    }

    public Superhero deleteSuperhero(String heroName) {
        Superhero superhero = getSuperhero(heroName);
        int index = superheroes.indexOf(superhero);
        superheroes.remove(index);
        return superhero;
    }


    public String toString() {
        if (superheroes.size() > 0) {
            StringBuilder databaseString = new StringBuilder();
            for (Superhero superhero : superheroes) {
                databaseString.append(superhero.toString());
            }
            return ("\nList of Superheroes: \n-----------------\n" + databaseString);
        } else {
            return "The database is empty.";
        }
    }
}
