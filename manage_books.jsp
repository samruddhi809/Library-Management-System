<%@ page import="java.util.*, com.library.dao.BookDAO, com.library.model.Book" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center">📚 Manage Books</h2>

    <% if (request.getParameter("success") != null) { %>
        <div class="alert alert-success"><%= request.getParameter("success") %></div>
    <% } %>
    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger"><%= request.getParameter("error") %></div>
    <% } %>

    <!-- Add Book -->
    <h4>Add Book</h4>
    <form action="AddBookServlet" method="post" class="row g-2">
        <input type="text" name="title" placeholder="Title" required class="form-control" />
        <input type="text" name="author" placeholder="Author" required class="form-control" />
        <input type="text" name="isbn" placeholder="ISBN" required class="form-control" />
        <input type="number" name="quantity" placeholder="Quantity" required class="form-control" />
        <input type="number" name="publicationYear" placeholder="Publication Year" required class="form-control" />
        <button type="submit" class="btn btn-primary mt-2">Add Book</button>
    </form>

    <!-- Book Catalog -->
    <h4 class="mt-5">📖 Library Catalog</h4>
    <table class="table table-bordered table-striped">
        <thead>
            <tr><th>ID</th><th>Title</th><th>Author</th><th>ISBN</th><th>Year</th><th>Qty</th><th>Actions</th></tr>
        </thead>
        <tbody>
        <%
            BookDAO dao = new BookDAO();
            List<Book> books = dao.getAllBooks();
            for (Book b : books) {
        %>
            <tr>
                <td><%= b.getBookId() %></td>
                <td><%= b.getTitle() %></td>
                <td><%= b.getAuthor() %></td>
                <td><%= b.getIsbn() %></td>
                <td><%= b.getPublicationYear() %></td>
                <td><%= b.getQuantity() %></td>
                <td>
                    <a href="edit_book.jsp?bookId=<%= b.getBookId() %>" class="btn btn-warning btn-sm">Edit</a>
                    <a href="DeleteBookServlet?bookId=<%= b.getBookId() %>" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
