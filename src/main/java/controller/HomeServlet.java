package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.BlogDAO;
import util.DatabaseConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.Blog;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            BlogDAO blogDAO = new BlogDAO(DatabaseConnection.getConnection());
            List<Blog> blogs = blogDAO.getAllBlogs();
            request.setAttribute("blogs", blogs);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}