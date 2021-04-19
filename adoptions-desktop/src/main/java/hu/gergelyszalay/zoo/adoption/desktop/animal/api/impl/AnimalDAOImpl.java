package hu.gergelyszalay.zoo.adoption.desktop.animal.api.impl;

import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.AnimalDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    // SQL Statements
    private static final String SELECT_ALL_ANIMALS = "SELECT * FROM ANIMALS";
    private static final String INSERT_ANIMAL = "INSERT INTO ANIMALS " +
            "(id, name, species, introduction, birth_year) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ANIMAL = "UPDATE ANIMALS " +
            "SET name=?, species = ?, introduction = ?, birth_year=? WHERE id=?";
    private static final String DELETE_ANIMAL = "DELETE FROM ANIMALS WHERE id = ?";
    private final String connectionURL;

    public AnimalDAOImpl() {
        connectionURL = ZooAdoptionsConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Animal> findAll() {

        List<Animal> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_ANIMALS)
        ) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setName(rs.getString("name"));
                animal.setSpecies(rs.getString("species"));
                animal.setIntroduction(rs.getString("introduction"));
                animal.setBirthYear(rs.getInt("birthYear"));

                result.add(animal);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;

    }


    @Override
    public Animal save(Animal animal) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = animal.getId() <= 0 ?
                     c.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS) :
                     c.prepareStatement(UPDATE_ANIMAL)
        ) {
            if (animal.getId() > 0) { // UPDATE
                stmt.setInt(5, animal.getId());
            }

            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getSpecies());
            stmt.setString(3, animal.getIntroduction());
            stmt.setString(4, String.valueOf(animal.getBirthYear()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if (animal.getId() <= 0) { // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()) {
                    animal.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return animal;
    }

    @Override
    public void delete(Animal animal) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_ANIMAL)
        ) {
            stmt.setInt(1, animal.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}