package dto;

import java.util.List;

public class DTOCityName_HeroName {
    String cityName;
    List<String> heroNames;

    public DTOCityName_HeroName(String cityName, List<String> heroName) {
        this.cityName = cityName;
        this.heroNames = heroName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<String> getHeroName() {
        return heroNames;
    }

    public void addHeroName(String heroName){
        heroNames.add(heroName);
    }
}
