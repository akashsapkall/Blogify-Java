package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import model.User;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

import dao.BlogDAO;

/**
 * Servlet implementation class AddBlogServlet
 */
@WebServlet("/add-blog")
public class AddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
     // Example error handling
//        if (blog.getTitle().isEmpty()) {
//            response.sendRedirect("add-blog.jsp?error=1");
//            return;
//        }

//        try {
//            // Save blog
//            response.sendRedirect("add-blog.jsp?success=1");
//        } catch (SQLException e) {
//            response.sendRedirect("add-blog.jsp?error=2");
//        }
        System.out.println(user);
        Blog blog = new Blog();
        blog.setTitle(request.getParameter("title"));
        blog.setShortDesc(request.getParameter("shortDesc"));
        blog.setContent(request.getParameter("content"));
        blog.setAuthorId(user.getId());

        try {
            new BlogDAO(DatabaseConnection.getConnection()).createBlog(blog);
            response.sendRedirect("home");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}