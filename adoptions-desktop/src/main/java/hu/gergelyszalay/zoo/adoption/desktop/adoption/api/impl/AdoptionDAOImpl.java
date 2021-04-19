package hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.AdoptionDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionDAOImpl implements AdoptionDAO {

    // SQL Statements
    private static final String SELECT_ALL_ADOPTATIONS = "SELECT * FROM ADOPTATIONS";
    private static final String INSERT_ADOPTATION = "INSERT INTO ADOPTATIONS " +
            "(id, animalId, adopterId, adoptionDate, supportType, supportAmount) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ADOPTATION = "UPDATE ADOPTATIONS " +
            "SET animalId=?, adopterId = ?, adoptionDate = ?, supportType=?, supportAmount=? WHERE id=?";
    private static final String DELETE_ADOPTATION = "DELETE FROM ADOPTATIONS WHERE id = ?";
    private final String connectionURL;

    public AdoptionDAOImpl() {
        connectionURL = ZooAdoptionsConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Adoption> findAll() {

        List<Adoption> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_ADOPTATIONS)
        ) {

            while (rs.next()) {
                Adoption adoption = new Adoption();
                adoption.setId(rs.getInt("id"));
                adoption.setAnimalId(rs.getInt("animalId"));
                adoption.setAdopterId(rs.getInt("adopterId"));
                Date date = Date.valueOf(rs.getString("adoptionDate"));
                adoption.setAdoptionDate(date == null ? LocalDate.now() : date.toLocalDate());
                adoption.setSupportType(rs.getString("supportType"));
                adoption.setSupportAmount(rs.getInt("supportAmount"));

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
                     c.prepareStatement(INSERT_ADOPTATION, Statement.RETURN_GENERATED_KEYS) :
                     c.prepareStatement(UPDATE_ADOPTATION)
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
             PreparedStatement stmt = c.prepareStatement(DELETE_ADOPTATION)
        ) {
            stmt.setInt(1, adoption.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
