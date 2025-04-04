<%@include file="includes/header.jsp"%>
<div class="main-content">
    <div class="auth-container">
        <h2>Sign Up</h2>
        
        <c:if test="${not empty param.error}">
            <div class="auth-message error-message">
                <c:choose>
                    <c:when test="${param.error == 1}">
                        Email already exists!
                    </c:when>
                    <c:otherwise>
                        Registration failed. Please try again.
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        
        <form class="auth-form" action="signup" method="post">
            <input type="text" name="fullName" placeholder="Full Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" class="btn">Sign Up</button>
        </form>
        
        <div class="auth-links">
            <p>Already have an account? <a href="login.jsp">Login</a></p>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>