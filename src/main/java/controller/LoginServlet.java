package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = new UserDAO(DatabaseConnection.getConnection()).getUserByEmail(email);
            
            if (user != null && checkPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("home");  // Changed to home servlet
                return;  // Critical: Stop execution
            } else {
                response.sendRedirect("login.jsp?error=1");
                return;  // Critical: Stop execution
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2");
            return;  // Handle database errors
        }
    }

    private boolean checkPassword(String plainPassword, String storedPassword) {
        // Temporary plain text comparison (replace with BCrypt in production)
        return plainPassword.equals(storedPassword);
    }
}