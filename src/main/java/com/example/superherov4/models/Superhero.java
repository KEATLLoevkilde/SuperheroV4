package com.example.superherov4.models;


public class Superhero {

    private String heroName;
    private String privateName;
    private int creationYear;

    public Superhero(String heroName, String privateName, int creationYear) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.creationYear = creationYear;
    }

    //Get methods
    public String getHeroName() {
        return heroName;
    }
    public String getPrivateName() {
        return privateName;
    }
    public int getCreationYear() {
        return creationYear;
    }

    //Set methods
    public void setHeroName(String heroName) {
        if (!heroName.isEmpty()) {
            this.heroName = heroName;
        } else {
            this.heroName = "";
        }
    }

    public void setPrivateName(String privateName) { this.privateName = privateName;}


}
