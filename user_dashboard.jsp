<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("bookList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<!-- ✅ Popup Message for Borrow Confirmation -->
<%
    String message = request.getParameter("message");
    if (message != null) {
%>
<script>alert("<%= message %>");</script>
<% } %>

<div class="container mt-5">
    <h2 class="text-center text-primary mb-4">📚 User Dashboard</h2>

    <!-- 1. Browse Library -->
    <div class="mb-4">
        <h4>📖 Library Catalog</h4>
        <form action="UserDashboardServlet" method="GET" class="input-group mb-3">
            <input type="text" name="query" class="form-control me-2" placeholder="Search books..." required />
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <table class="table table-bordered">
            <thead class="table-secondary">
                <tr><th>ID</th><th>Title</th><th>Author</th><th>Quantity</th><th>Action</th></tr>
            </thead>
            <tbody>
                <%
                    if (books != null && !books.isEmpty()) {
                        for (Book b : books) {
                %>
                <tr>
                    <td><%= b.getBookId() %></td>
                    <td><%= b.getTitle() %></td>
                    <td><%= b.getAuthor() %></td>
                    <td><%= b.getQuantity() %></td>
                    <td>
                        <% if (b.getQuantity() > 0) { %>
                        <form action="BorrowBookServlet" method="POST">
                            <input type="hidden" name="bookId" value="<%= b.getBookId() %>"/>
                            <button class="btn btn-success btn-sm">Borrow</button>
                        </form>
                        <% } else { %>
                        <span class="text-danger">Out of Stock</span>
                        <% } %>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="5" class="text-center text-danger">No books available.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <!-- 2. Return Book -->
    <div class="mb-4">
        <h4>🔁 Return Book</h4>
        <form action="ReturnBookServlet" method="POST" class="d-flex">
            <input type="number" name="bookId" placeholder="Book ID" class="form-control me-2" required />
            <button class="btn btn-warning text-white">Return</button>
        </form>
    </div>

    <a href="logout.jsp" class="btn btn-danger">Logout</a>
</div>
</body>
</html>
