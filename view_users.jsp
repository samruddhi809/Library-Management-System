<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.library.model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registered Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center text-primary">👥 Registered Users</h2>
    <table class="table table-bordered table-striped mt-4">
        <thead class="table-light">
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <% if (users != null && !users.isEmpty()) {
               for (User u : users) { %>
            <tr>
                <td><%= u.getUserId() %></td>
                <td><%= u.getUsername() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getRole() %></td>
                <td>
                    <form action="ViewUsersServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this user?');">
                        <input type="hidden" name="deleteUserId" value="<%= u.getUserId() %>">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        <%   }
           } else { %>
            <tr><td colspan="5" class="text-center text-danger">No users found.</td></tr>
        <% } %>
        </tbody>
    </table>
    <div class="text-center mt-3">
        <a href="admin_dashboard.jsp" class="btn btn-secondary">⬅ Back to Dashboard</a>
    </div>
</div>
</body>
</html>
