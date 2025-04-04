<%@include file="includes/header.jsp"%>
<div class="main-content">
    <h2>Add New Blog</h2>
    <form action="add-blog" method="post">
        <input type="text" name="title" placeholder="Blog Title" required>
        <textarea name="shortDesc" placeholder="Short Description" required></textarea>
        <textarea name="content" placeholder="Blog Content" rows="10" required></textarea>
        <button type="submit" class="btn">Publish Blog</button>
    </form>
</div>
<%@include file="includes/footer.jsp"%>