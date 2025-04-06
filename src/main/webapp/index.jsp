
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
                    <p class="author"><span><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#999999"><path d="M480-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T560-640q0-33-23.5-56.5T480-720q-33 0-56.5 23.5T400-640q0 33 23.5 56.5T480-560Zm0-80Zm0 400Z"/></svg></span> ${blog.authorName}</p>
                    <p class="shrt-dsc">${blog.shortDesc}</p>
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