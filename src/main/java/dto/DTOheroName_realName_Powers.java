package dto;

import java.util.List;

public class DTOheroName_realName_Powers {
    private String heroName;
    private String realName;
    private List<String> superPowers;

    public DTOheroName_realName_Powers(String heroName, String realName, List<String> superPowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.superPowers = superPowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getSuperPowers() {
        return superPowers;
    }

    public void addSuperPower(String superPower){
        superPowers.add(superPower);
    }
}
