package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.*;

public class UserDAO {
    private Connection con;
    
    public UserDAO(Connection con) {
        this.con = con;
    }
    
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }
        return null;
    }
    
    public boolean createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection(); // Get fresh connection
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        user.setCreatedAt(generatedKeys.getTimestamp("created_at").toLocalDateTime());
                    }
                }
                return true;
            }
            return false;
        } // Connection auto-closed here
    }
}