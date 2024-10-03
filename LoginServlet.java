import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching login details from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Attempt to log the user in
        boolean isAuthenticated = Main.loginUser(email, password);

        if (isAuthenticated) {
            // If login is successful, redirect to a homepage/dashboard (example: home.jsp)
            response.sendRedirect("home.jsp");
        } else {
            // If login fails, forward back to the login page with an error message
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
