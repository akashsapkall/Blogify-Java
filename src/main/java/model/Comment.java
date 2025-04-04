package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Comment {
    private int id;
    private int blogId;
    private int userId;
    private String comment;
    private LocalDateTime createdAt;
    private String userName;
    
    // Getters
    public int getId() { return id; }
    public int getBlogId() { return blogId; }
    public int getUserId() { return userId; }
    public String getComment() { return comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getUserName() { return userName; }
    public Date getCreatedAtAsDate() {
        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
    }
    // Setters
    public void setId(int id) { this.id = id; }
    public void setBlogId(int blogId) { this.blogId = blogId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setComment(String comment) { this.comment = comment; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUserName(String userName) { this.userName = userName; }
}