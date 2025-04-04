package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.DatabaseConnection;
import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password); // Remember to hash in production

        try {
            UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());
            boolean success = userDAO.createUser(user);

            if (success) {
                response.sendRedirect("login.jsp");
                return; // Critical: Stop further execution
            } else {
                response.sendRedirect("signup.jsp?error=1");
                return; // Critical: Stop further execution
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=2");
            return; // Critical: Stop further execution
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=3");
            return; // Catch-all for other exceptions
        }
    }
}