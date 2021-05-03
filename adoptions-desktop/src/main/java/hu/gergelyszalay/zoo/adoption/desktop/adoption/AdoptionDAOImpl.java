package hu.gergelyszalay.zoo.adoption.desktop.adoption;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.config.ZooAdoptionsConfiguration;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionDAOImpl implements AdoptionDAO {

    private static final String SELECT_ALL_ADOPTIONS =
            "SELECT * FROM ADOPTIONS";
    private static final String INSERT_ADOPTION =
            "INSERT INTO ADOPTIONS " +
                    "(animal_Id, adopter_Id, date, support_type, support_amount, support_frequency) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ADOPTION =
            "UPDATE ADOPTIONS " +
                    "SET animal_Id=?, adopter_Id = ?, date = ?, support_type=?, support_amount=?, support_frequency=? WHERE id=?";
    private static final String DELETE_ADOPTION =
            "DELETE FROM ADOPTIONS WHERE id = ?";
    private static final String COUNT_ADOPTED_ANIMALS =
            "SELECT COUNT( DISTINCT animal_Id) as animals FROM adoptions";
    private static final String COUNT_ADOPTERS =
            "SELECT COUNT( DISTINCT adopter_Id) as adopters FROM adoptions";
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
            assignQueryResults(result, rs);

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
                stmt.setInt(7, adoption.getId());
            }

            stmt.setString(1, String.valueOf(adoption.getAnimalId()));
            stmt.setString(2, String.valueOf(adoption.getAdopterId()));
            stmt.setString(3, adoption.getAdoptionDate().toString());
            stmt.setString(4, String.valueOf(adoption.getSupportType()));
            stmt.setString(5, String.valueOf(adoption.getSupportAmount()));
            stmt.setString(6, String.valueOf(adoption.getSupportFrequency()));

            int affectedRows;
            affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if (adoption.getId() <= 0) {
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
        try (
                Connection c = DriverManager.getConnection(connectionURL);
                PreparedStatement stmt = c.prepareStatement(DELETE_ADOPTION)
        ) {
            stmt.setInt(1, adoption.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Adoption> findByAdopterId(Integer adopterId) {
        List<Adoption> result = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(
                     "SELECT * FROM adoptions where adoptions.adopter_id=?")
        ) {

            stmt.setString(1, adopterId.toString());
            ResultSet rs = stmt.executeQuery();
            assignQueryResults(result, rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Adoption> findByAnimalId(Integer animalId) {
        List<Adoption> result = new ArrayList<>();
        try (
                Connection c = DriverManager.getConnection(connectionURL);
                PreparedStatement stmt = c.prepareStatement(
                        "SELECT * FROM adoptions where adoptions.animal_id=?"
                )
        ) {
            stmt.setString(1, animalId.toString());
            ResultSet rs = stmt.executeQuery();
            assignQueryResults(result, rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Adoption> findBySupportType(String supportType) {
        List<Adoption> result = new ArrayList<>();
        try (
                Connection c = DriverManager.getConnection(connectionURL);
                PreparedStatement stmt = c.prepareStatement(
                        "SELECT * FROM adoptions where adoptions.support_type=?")
        ) {
            stmt.setString(1, supportType);
            ResultSet rs = stmt.executeQuery();
            assignQueryResults(result, rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Adoption> searchByYear(Integer year) {
        List<Adoption> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(
                     "SELECT * FROM adoptions WHERE date>=? AND  date<?")
        ) {
            stmt.setString(1, year + "-01-01");
            stmt.setString(2, (year + 1) + "-01-01");
            ResultSet rs = stmt.executeQuery();
            assignQueryResults(result, rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public Integer countAdoptedAnimals() {
        int totalAnimals = -1;
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
        int totalAdopters = -1;

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

    private void assignQueryResults(List<Adoption> result, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Adoption adoption = new Adoption();

            adoption.setId(rs.getInt("id"));
            adoption.setAnimalId(rs.getInt("animal_Id"));
            adoption.setAdopterId(rs.getInt("adopter_Id"));
            LocalDate date = LocalDate.parse(rs.getString("date"));
            adoption.setAdoptionDate(date == null ? LocalDate.now() : date);
            adoption.setSupportType(rs.getString("support_type"));
            adoption.setSupportAmount(rs.getInt("support_amount"));
            adoption.setSupportFrequency(rs.getString("support_frequency"));

            result.add(adoption);
        }
    }

}
