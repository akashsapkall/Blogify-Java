<%@ page language="java" contentType="text/html charset=UTF-8" 
	import="model.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Application</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <nav>
        <div class="logo"><a href="home">Blogify</a></div>
        <div class="nav-links">
            <a href="home">Home</a>
            <a href="about.jsp">About</a>
            <% if(user == null) { %>
                <a href="login.jsp">Login</a>
                <a href="signup.jsp">Sign Up</a>
            <% } else { %>
                <a href="add-blog.jsp">Add Blog</a>
                <div class="profile-dropdown">
                    <a href="#"><%= user.getFullName() %></a>
                    <div class="profile-menu">
                        <a href="logout">Logout</a>
                    </div>
                </div>
            <% } %>
        </div>
    </nav>