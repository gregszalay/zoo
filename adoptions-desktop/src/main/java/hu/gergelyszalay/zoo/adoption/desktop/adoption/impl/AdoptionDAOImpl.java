package hu.gergelyszalay.zoo.adoption.desktop.adoption.impl;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.config.ZooAdoptionsConfiguration;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionDAOImpl implements AdoptionDAO {

    // SQL Statements
    private static final String SELECT_ALL_ADOPTIONS = "SELECT * FROM ADOPTIONS";
    private static final String INSERT_ADOPTION = "INSERT INTO ADOPTIONS " +
            "(animal_Id, adopter_Id, date, support_type, support_amount) VALUES (?,?,?,?,?)";
    private static final String UPDATE_ADOPTION = "UPDATE ADOPTIONS " +
            "SET animal_Id=?, adopter_Id = ?, date = ?, support_type=?, support_amount=? WHERE id=?";
    private static final String DELETE_ADOPTION = "DELETE FROM ADOPTIONS WHERE id = ?";
    private static final String COUNT_ADOPTED_ANIMALS = "SELECT COUNT( DISTINCT animal_Id) as animals FROM adoptions";
        private static final String COUNT_ADOPTERS= "SELECT COUNT( DISTINCT adopter_Id) as adopters FROM adoptions";

    private final String connectionURL;

    public AdoptionDAOImpl() {
        connectionURL = ZooAdoptionsConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Adoption> findAll() {

        List<Adoption> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_ADOPTIONS)
        ) {

            while (rs.next()) {
                Adoption adoption = new Adoption();
                adoption.setId(rs.getInt("id"));
                adoption.setAnimalId(rs.getInt("animal_Id"));
                adoption.setAdopterId(rs.getInt("adopter_Id"));
                LocalDate date = /*rs.getDate("date");*/
                     LocalDate.parse(rs.getString("date"));
                //new DateFormat() pleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                adoption.setAdoptionDate(date == null ? LocalDate.now() : date);
                adoption.setSupportType(rs.getString("support_type"));
                adoption.setSupportAmount(rs.getInt("support_amount"));

                result.add(adoption);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;

    }


    @Override
    public Adoption save(Adoption adoption) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = adoption.getId() <= 0 ?
                     c.prepareStatement(INSERT_ADOPTION, Statement.RETURN_GENERATED_KEYS) :
                     c.prepareStatement(UPDATE_ADOPTION)
        ) {
            if (adoption.getId() > 0) { // UPDATE
                stmt.setInt(6, adoption.getId());
            }

            stmt.setString(1, String.valueOf(adoption.getAnimalId()));
            stmt.setString(2, String.valueOf(adoption.getAdopterId()));
            stmt.setString(3, adoption.getAdoptionDate().toString());
            stmt.setString(4, String.valueOf(adoption.getSupportType()));
            stmt.setString(5, String.valueOf(adoption.getSupportAmount()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if (adoption.getId() <= 0) { // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()) {
                    adoption.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return adoption;
    }

    @Override
    public void delete(Adoption adoption) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_ADOPTION)
        ) {
            stmt.setInt(1, adoption.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Integer countAdoptedAnimals() {

        Integer totalAnimals = -1;

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(COUNT_ADOPTED_ANIMALS)
        ) {

            while (rs.next()) {
                totalAnimals = rs.getInt("animals");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return totalAnimals;

    }


    public Integer countAdopters() {

        Integer totalAdopters = -1;

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(COUNT_ADOPTERS)
        ) {

            while (rs.next()) {
                totalAdopters = rs.getInt("adopters");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return totalAdopters;

    }





}
