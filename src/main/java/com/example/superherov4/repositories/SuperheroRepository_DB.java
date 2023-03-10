package com.example.superherov4.repositories;

import com.example.superherov4.models.Superhero;
import dto.DTOCityName_HeroName;
import dto.DTOheroName_RealName_NumPowers;
import dto.DTOheroName_realName_Powers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheroRepository_DB {
    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String db_username;

    @Value("${spring.datasource.password}")
    private String db_password;

    //Superheroes
    public Superhero getSuperhero(String heroName){
        Superhero superhero = null;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            String sql = "SELECT heroName, realName, creationYear FROM superhero WHERE heroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String heroName1 = resultSet.getString("heroName");
                String realName = resultSet.getString("realName");
                int creationYear = resultSet.getInt("creationYear");
                superhero = new Superhero(heroName1, realName, creationYear);
            }
            return superhero;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Superhero> getSuperheroes() {
        List<Superhero> superheroes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            String sql = "SELECT heroName, realName, creationYear FROM superhero";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String heroName = resultSet.getString("heroName");
                String realName = resultSet.getString("realName");
                int creationYear = resultSet.getInt("creationYear");
                superheroes.add(new Superhero(heroName, realName, creationYear));
            }
            return superheroes;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Number of powers
    public DTOheroName_RealName_NumPowers getHeroNamesAndNumOfPowers(String heroName){
        DTOheroName_RealName_NumPowers dto = null;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT heroName, realName, count(powerID)" +
                    " FROM superhero JOIN superpower_superhero USING(heroID)" +
                    " GROUP BY heroID HAVING heroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String newHeroName = resultSet.getString("heroName");
                String newRealName = resultSet.getString("realName");
                int newNumPow = resultSet.getInt("count(powerID)");
                dto = new DTOheroName_RealName_NumPowers(newHeroName, newRealName, newNumPow);
            }
            return dto;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<DTOheroName_RealName_NumPowers> getAllHeroNamesAndNumOfPowers(){
        List<DTOheroName_RealName_NumPowers> dtoList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT heroName, realName, count(powerID)\n" +
                    "FROM superhero JOIN superpower_superhero USING(heroID)\n" +
                    "GROUP BY heroID";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String newHeroName = resultSet.getString("heroName");
                String newRealName = resultSet.getString("realName");
                int newNumPow = resultSet.getInt("count(powerID)");
                DTOheroName_RealName_NumPowers dto = new DTOheroName_RealName_NumPowers(newHeroName, newRealName, newNumPow);
                if(!dtoList.contains(dto)){
                    dtoList.add(dto);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dtoList;
    }

    // Powers
    public DTOheroName_realName_Powers getHeroNamesAndPowers(String heroName){
        DTOheroName_realName_Powers dto = null;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT heroName, realName, powerName\n" +
                    "FROM superhero JOIN superpower_superhero ON superhero.heroID = superpower_superhero.heroID\n" +
                    "JOIN superpower ON superpower_superhero.powerID = superpower.powerID\n" +
                    "WHERE heroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String newHeroName = resultSet.getString("heroName");
                String newRealName = resultSet.getString("realName");
                String newPower = resultSet.getString("powerName");

                if(dto != null){
                    dto.addSuperPower(newPower);
                }else{
                    dto = new DTOheroName_realName_Powers(newHeroName, newRealName, new ArrayList<>(List.of(newPower)));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dto;

    }
    public List<DTOheroName_realName_Powers> getAllHeroNamesAndPowers(){
        List<DTOheroName_realName_Powers> dtoList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT heroName, realName, powerName" +
            " FROM superhero JOIN superpower_superhero USING(heroID) JOIN superpower USING(powerID)" +
            " ORDER BY heroName";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            String currentHeroName = "";
            DTOheroName_realName_Powers currentDTO = null;

            while (resultSet.next()){
                String newHeroName = resultSet.getString("heroName");
                String newRealName = resultSet.getString("realName");
                String newPower = resultSet.getString("powerName");

                if (newHeroName.equals(currentHeroName)){
                    currentDTO.addSuperPower(newPower);
                }else {
                    currentDTO = new DTOheroName_realName_Powers(newHeroName, newRealName, new ArrayList<>(List.of(newPower)));
                    currentHeroName = newHeroName;
                    dtoList.add(currentDTO);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dtoList;
    }

    // Cities
    public DTOCityName_HeroName getCityNameAndHeroes(String cityName){
        DTOCityName_HeroName dto = null;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT cityName, heroName\n" +
                    "FROM city join superhero USING(cityID)\n" +
                    "WHERE cityName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cityName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String newCityName = resultSet.getString("cityName");
                String newHeroName = resultSet.getString("heroName");

                if(dto != null){
                    dto.addHeroName(newHeroName);
                }else{
                    dto = new DTOCityName_HeroName(newCityName, new ArrayList<>(List.of(newHeroName)));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dto;
    }
    public List<DTOCityName_HeroName> getAllCityNamesAndHeroes(){
        List<DTOCityName_HeroName> dtoList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password)){
            String sql = "SELECT cityName, heroName\n" +
                    "FROM city join superhero USING(cityID)\n" +
                    "ORDER BY cityName";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            String currentCityName = "";
            DTOCityName_HeroName currentDTO = null;

            while (resultSet.next()){
                String newCityName = resultSet.getString("cityName");
                String newHeroName = resultSet.getString("heroName");

                if (newCityName.equals(currentCityName)){
                    currentDTO.addHeroName(newHeroName);
                }else {
                    currentDTO = new DTOCityName_HeroName(newCityName, new ArrayList<>(List.of(newHeroName)));
                    currentCityName = newCityName;
                    dtoList.add(currentDTO);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dtoList;
    }
}





