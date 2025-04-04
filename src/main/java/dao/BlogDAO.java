package dao;

import model.Blog;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    private Connection con;
    
    public BlogDAO(Connection con) {
        this.con = con;
    }
    
 // In BlogDAO.java
    public List<Blog> getAllBlogs() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT b.*, u.full_name as author_name FROM blogs b " +
                     "JOIN users u ON b.author_id = u.id ORDER BY b.created_at DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setShortDesc(rs.getString("short_desc"));
                blog.setContent(rs.getString("content"));
                blog.setAuthorId(rs.getInt("author_id"));
                blog.setAuthorName(rs.getString("author_name"));
                blog.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                blogs.add(blog);
            }
        }
        return blogs;
    }
    public Blog getBlogById(int id) throws SQLException {
        String sql = "SELECT b.*, u.full_name as author_name FROM blogs b " +
                    "JOIN users u ON b.author_id = u.id WHERE b.id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setShortDesc(rs.getString("short_desc"));
                blog.setContent(rs.getString("content"));
                blog.setAuthorId(rs.getInt("author_id"));
                blog.setAuthorName(rs.getString("author_name")); // Now getting from JOIN
                blog.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return blog;
            }
        }
        return null;
    }
    public boolean createBlog(Blog blog) throws SQLException {
        String sql = "INSERT INTO blogs (title, short_desc, content, author_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getShortDesc());
            ps.setString(3, blog.getContent());
            ps.setInt(4, blog.getAuthorId());
            return ps.executeUpdate() > 0;
        }
    }
    
    // Add similar methods for getBlogById, createBlog, etc.
}