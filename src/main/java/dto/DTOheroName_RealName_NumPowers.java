package dto;

public class DTOheroName_RealName_NumPowers {
    private String heroName;
    private String realName;
    private int numberOfPowers;

    public DTOheroName_RealName_NumPowers(String heroName, String realName, int numberOfPowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.numberOfPowers = numberOfPowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public int getNumberOfPowers() {
        return numberOfPowers;
    }
}
