import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/sustainable_living";
    private static final String USER = "root";
    private static final String PASSWORD = "Chandana@2005";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Update the method to accept a user ID
    public static boolean registerUser(String userId, String firstName, String lastName, String email, String password, String phoneNumber) {
        String sql = "INSERT INTO users (user_id, first_name, last_name, email, password, phone_number) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId); // Set user ID
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, password); // Consider hashing the password
            pstmt.setString(6, phoneNumber);

            int rowsAffected = pstmt.executeUpdate(); // Execute the INSERT statement

            return rowsAffected > 0; // Return true if a row was inserted

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate email error
                System.out.println("Error: A user with this email already exists.");
            } else {
                e.printStackTrace();
            }
            return false; // Return false in case of failure
        }
    }

    public static boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Returns true if the user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Example usage: Registering a new user
        boolean isRegistered = registerUser("1001", "John", "Doe", "john@example.com", "password123", "1234567890");
        if (isRegistered) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("User registration failed.");
        }

        // Example usage: Logging in a user
        if (loginUser("john@example.com", "password123")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}
