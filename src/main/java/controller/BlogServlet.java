package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.stream.events.Comment;

import dao.BlogDAO;
import dao.CommentDAO;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int blogId = Integer.parseInt(request.getParameter("id"));
        try {
            Blog blog = new BlogDAO(DatabaseConnection.getConnection()).getBlogById(blogId);
            List<model.Comment> comments = new CommentDAO(DatabaseConnection.getConnection()).getCommentsByBlogId(blogId);
            
            request.setAttribute("blog", blog);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("blog.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}