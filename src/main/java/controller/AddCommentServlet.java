package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.User;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

import dao.CommentDAO;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        String commentText = request.getParameter("comment");
        
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setUserId(user.getId());
        comment.setComment(commentText);
        
        try {
            new CommentDAO(DatabaseConnection.getConnection()).createComment(comment);
            response.sendRedirect("blog?id=" + blogId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}