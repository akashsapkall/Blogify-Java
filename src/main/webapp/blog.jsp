<%@include file="includes/header.jsp"%>
<div class="main-content">
    <div class="blog-card">
        <h1>${blog.title}</h1>
        <br>
        <p>${blog.content}</p>
        <p class="meta author"> <span><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#7A2917"><path d="M480-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T560-640q0-33-23.5-56.5T480-720q-33 0-56.5 23.5T400-640q0 33 23.5 56.5T480-560Zm0-80Zm0 400Z"/></svg></span> ${blog.authorName} - <fmt:formatDate value="${blog.createdAtAsDate}" pattern="dd MMM yyyy"/></p>
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