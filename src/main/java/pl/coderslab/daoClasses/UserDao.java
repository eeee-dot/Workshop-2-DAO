package pl.coderslab.daoClasses;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtils;
import pl.coderslab.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";

    public User create(User user) {
        try (Connection conn = DbUtils.connect()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int id) {
        try (Connection conn = DbUtils.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(id);
                user.setUsername(rs.getString(3));
                user.setEmail(rs.getString(2));
                user.setPassword(hashPassword(rs.getString(4)));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try(Connection conn = DbUtils.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtils.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
