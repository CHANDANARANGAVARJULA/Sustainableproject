import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching user details from the signup form
        String userId = request.getParameter("userId"); // New parameter for user ID
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        // Register user and check the result
        boolean isRegistered = Main.registerUser(userId, firstName, lastName, email, password, phoneNumber);

        if (isRegistered) {
            // If registration is successful, redirect to the login page
            response.sendRedirect("login.html");
        } else {
            // If registration fails, forward back to the signup page with an error message
            request.setAttribute("errorMessage", "Registration failed. Email might already be in use.");
            request.getRequestDispatcher("signup.html").forward(request, response);
        }
    }
}
