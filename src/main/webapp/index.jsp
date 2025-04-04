
<%@include file="includes/header.jsp"%>
<c:if test="${empty blogs}">
    <c:redirect url="/home"/>
</c:if>
<div class="main-content">
    <c:if test="${not empty param.error}">
        <div class="error-message">
            <c:choose>
                <c:when test="${param.error == 'fetch_error'}">
                    Error loading blogs!
                </c:when>
            </c:choose>
        </div>
    </c:if>
    
<h1>Recent Blogs</h1>
<div class="blogs-container">
    <c:choose>
        <c:when test="${not empty blogs}">
            <c:forEach items="${blogs}" var="blog">
                <div class="blog-card">
                    <h3>${blog.title}</h3>
                    <p class="author">By ${blog.authorName}</p>
                    <p>${blog.shortDesc}</p>
                    <div class="meta">
                        <small>Posted on: 
                            <fmt:formatDate value="${blog.createdAtAsDate}" pattern="dd MMM yyyy HH:mm"/>
                        </small>
                    </div>
                    <a href="blog?id=${blog.id}" class="btn">Read More</a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No blogs found!</p>
        </c:otherwise>
    </c:choose>
</div>
</div>
<%@include file="includes/footer.jsp"%>