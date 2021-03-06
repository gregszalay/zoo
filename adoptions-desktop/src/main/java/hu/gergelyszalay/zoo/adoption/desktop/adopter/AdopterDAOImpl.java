package hu.gergelyszalay.zoo.adoption.desktop.adopter;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAO;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.config.ZooAdoptionsConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdopterDAOImpl implements AdopterDAO {

    private static final String SELECT_ALL_ADOPTERS =
            "SELECT * FROM ADOPTERS";
    private static final String INSERT_ADOPTER =
            "INSERT INTO ADOPTERS " +
                    "(last_name, first_name, email, password) VALUES (?,?,?,?)";
    private static final String UPDATE_ADOPTER =
            "UPDATE ADOPTERS SET last_name=?, first_name = ?, email = ?, password = ? WHERE id=?";
    private static final String DELETE_ADOPTER =
            "DELETE FROM ADOPTERS WHERE id = ?";
    private final String connectionURL;

    public AdopterDAOImpl() {
        connectionURL = ZooAdoptionsConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Adopter> findAll() {
        List<Adopter> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_ADOPTERS)
        ) {
            while (rs.next()) {
                Adopter adopter = new Adopter();
                adopter.setId(rs.getInt("id"));
                adopter.setLastName(rs.getString("last_name"));
                adopter.setFirstName(rs.getString("first_name"));
                adopter.setEmail(rs.getString("email"));
                adopter.setPassword(rs.getString("password"));

                result.add(adopter);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Adopter> findUser(String email) {
        List<Adopter> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(
                     "SELECT * FROM ADOPTERS where email=? ")
        ) {
            stmt.setString(1, email);
            assignQueryResults(result, stmt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Adopter> findByName(String adopterName) {
        adopterName = adopterName.toLowerCase();
        List<Adopter> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM ADOPTERS " +
                     "where last_name LIKE ? OR first_name LIKE ? ")
        ) {
            stmt.setString(1, "%" + adopterName + "%");
            stmt.setString(2, "%" + adopterName + "%");
            assignQueryResults(result, stmt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Adopter> findUser(String email, String password) {
        List<Adopter> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             //PreparedStatement stmt = c.prepareStatement("SELECT * FROM ADOPTERS where email=? AND password=?")
             PreparedStatement stmt = c.prepareStatement(
                     "SELECT * FROM ADOPTERS where email=? AND password=?")
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            assignQueryResults(result, stmt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public Adopter save(Adopter adopter) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = adopter.getId() <= 0 ?
                     c.prepareStatement(INSERT_ADOPTER, Statement.RETURN_GENERATED_KEYS) :
                     c.prepareStatement(UPDATE_ADOPTER)
        ) {
            if (adopter.getId() > 0) { // UPDATE
                stmt.setInt(5, adopter.getId());
            }
            stmt.setString(1, String.valueOf(adopter.getLastName()));
            stmt.setString(2, String.valueOf(adopter.getFirstName()));
            stmt.setString(3, String.valueOf(adopter.getEmail()));
            stmt.setString(4, String.valueOf(adopter.getPassword()));

            int affectedRows;
            affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if (adopter.getId() <= 0) { // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()) {
                    adopter.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return adopter;
    }

    @Override
    public void delete(Adopter adopter) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_ADOPTER)
        ) {
            stmt.setInt(1, adopter.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void assignQueryResults(List<Adopter> result, PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Adopter adopter = new Adopter();
            adopter.setId(rs.getInt("id"));
            adopter.setLastName(rs.getString("last_name"));
            adopter.setFirstName(rs.getString("first_name"));
            adopter.setEmail(rs.getString("email"));
            adopter.setPassword(rs.getString("password"));

            result.add(adopter);
        }
    }


}
