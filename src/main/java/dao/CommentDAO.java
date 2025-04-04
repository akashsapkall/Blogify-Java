package dao;

import model.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Connection con;

    public CommentDAO(Connection con) {
        this.con = con;
    }

    public boolean createComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (blog_id, user_id, comment) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, comment.getBlogId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getComment());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Comment> getCommentsByBlogId(int blogId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.*, u.full_name as user_name FROM comments c " +
                     "JOIN users u ON c.user_id = u.id WHERE c.blog_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setBlogId(rs.getInt("blog_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setComment(rs.getString("comment"));
                comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                comment.setUserName(rs.getString("user_name"));
                comments.add(comment);
            }
        }
        return comments;
    }
}