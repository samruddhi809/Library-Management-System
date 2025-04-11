<%@ page import="com.library.dao.BookDAO, com.library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center text-primary">✏️ Edit Book</h2>
    <%
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDAO dao = new BookDAO();
        Book book = dao.getBookById(bookId);
    %>
    <form action="EditBookServlet" method="post" class="mt-4">
        <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
        <div class="mb-3">
            <label>Title</label>
            <input type="text" name="title" class="form-control" value="<%= book.getTitle() %>" required>
        </div>
        <div class="mb-3">
            <label>Author</label>
            <input type="text" name="author" class="form-control" value="<%= book.getAuthor() %>" required>
        </div>
        <div class="mb-3">
            <label>ISBN</label>
            <input type="text" name="isbn" class="form-control" value="<%= book.getIsbn() %>" required>
        </div>
        <div class="mb-3">
            <label>Quantity</label>
            <input type="number" name="quantity" class="form-control" value="<%= book.getQuantity() %>" required>
        </div>
        <div class="mb-3">
            <label>Publication Year</label>
            <input type="number" name="publicationYear" class="form-control" value="<%= book.getPublicationYear() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Book</button>
        <a href="manage_books.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
