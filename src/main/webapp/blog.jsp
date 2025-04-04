<%@include file="includes/header.jsp"%>
<div class="main-content">
    <div class="blog-card">
        <h1>${blog.title}</h1>
        <p>${blog.content}</p>
        <p class="meta">By ${blog.authorName} - <fmt:formatDate value="${blog.createdAtAsDate}" pattern="dd MMM yyyy"/></p>
    </div>

    <div class="comment-section">
    <h3>Comments</h3>
    <c:choose>
        <c:when test="${not empty comments}">
            <c:forEach items="${comments}" var="comment">
                <div class="comment">
                    <strong>${comment.userName}</strong>
                    <p>${comment.comment}</p>
                    <small>
    					<c:if test="${not empty comment.createdAtAsDate}">
        					<fmt:formatDate value="${comment.createdAtAsDate}" pattern="dd MMM yyyy HH:mm"/>
    					</c:if>
					</small>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No comments yet. Be the first to comment!</p>
        </c:otherwise>
    </c:choose>
        <form class="comment-form" action="add-comment" method="post">
            <input type="hidden" name="blogId" value="${blog.id}">
            <textarea name="comment" placeholder="Write your comment..." required></textarea>
            <button type="submit" class="btn">Post Comment</button>
        </form>
    </div>
</div>
<%@include file="includes/footer.jsp"%>