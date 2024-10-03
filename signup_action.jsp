<%@ page import="java.sql.*" %>
<%
    String userId = request.getParameter("userId"); // New parameter for user ID
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String phoneNumber = request.getParameter("phoneNumber");

    String dbURL = "jdbc:mysql://localhost:3306/sustainable_living";
    String dbUser = "admin";
    String dbPassword = "Chandana@2005";

    Connection conn = null;
    PreparedStatement pstmt = null;
    String message = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

        String sql = "INSERT INTO users (user_id, first_name, last_name, email, password, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userId); // Set user ID
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, email);
        pstmt.setString(5, password); // You should hash the password before storing it
        pstmt.setString(6, phoneNumber);

        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            response.sendRedirect("login.html");
        } else {
            message = "User registration failed!";
        }

    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        message = "An error occurred during registration: " + ex.getMessage();
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    if (!message.isEmpty()) {
        out.println("<p>" + message + "</p>");
        out.println("<a href='signup.html'>Try Again</a>");
    }
%>
