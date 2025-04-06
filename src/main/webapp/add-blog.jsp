<%@include file="includes/header.jsp"%>
<div class="main-content">
    <div class="blog-form-container">
        <h2>Add New Blog</h2>
        
        <%-- Error Message Display --%>
        <c:if test="${not empty param.error}">
            <div class="form-error">
                <c:choose>
                    <c:when test="${param.error == 1}">
                        Error: Blog title cannot be empty!
                    </c:when>
                    <c:when test="${param.error == 2}">
                        Error: Failed to save blog to database!
                    </c:when>
                    <c:otherwise>
                        Error: Please fill all required fields!
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        
        <%-- Success Message Display --%>
        <c:if test="${not empty param.success}">
            <div class="form-success">
                Blog published successfully!
            </div>
        </c:if>

        <form class="blog-form" action="add-blog" method="post">
            <input type="text" name="title" placeholder="Blog Title" required>
            <textarea name="shortDesc" placeholder="Short Description (Max 200 characters)" 
                     maxlength="200" required></textarea>
            <textarea name="content" placeholder="Blog Content (Markdown supported)" 
                     rows="10" required></textarea>
            <button type="submit" class="btn">Publish Blog</button>
        </form>
    </div>
</div>
<%@include file="includes/footer.jsp"%>