<%@include file="includes/header.jsp"%>
<div class="main-content">
    <div class="auth-container">
        <h2>Login</h2>
        
        <%-- Add error/success messages here if needed --%>
        <c:if test="${not empty param.error}">
            <div class="auth-message error-message">
                Invalid email or password!
            </div>
        </c:if>
        
        <form class="auth-form" action="login" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" class="btn">Login</button>
        </form>
        
        <div class="auth-links">
            <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>