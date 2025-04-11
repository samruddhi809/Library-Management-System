<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.library.model.BorrowRequest" %>
<%
    List<BorrowRequest> borrowHistory = (List<BorrowRequest>) request.getAttribute("borrowHistory");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Borrowing History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center text-primary mb-4">📚 My Borrowing History</h2>
    <table class="table table-bordered table-hover">
        <thead class="table-info">
            <tr>
                <th>Request ID</th>
                <th>Book Title</th>
                <th>Status</th>
                <th>Date Requested</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (borrowHistory != null && !borrowHistory.isEmpty()) {
                for (BorrowRequest r : borrowHistory) {
        %>
            <tr>
                <td><%= r.getRequestId() %></td>
                <td><%= r.getBookTitle() %></td>
                <td>
                    <% if ("approved".equalsIgnoreCase(r.getStatus())) { %>
                        <span class="badge bg-success">Approved</span>
                    <% } else if ("pending".equalsIgnoreCase(r.getStatus())) { %>
                        <span class="badge bg-warning text-dark">Pending</span>
                    <% } else if ("rejected".equalsIgnoreCase(r.getStatus())) { %>
                        <span class="badge bg-danger">Rejected</span>
                    <% } else if ("returned".equalsIgnoreCase(r.getStatus())) { %>
                        <span class="badge bg-secondary">Returned</span>
                    <% } else { %>
                        <span class="badge bg-dark"><%= r.getStatus() %></span>
                    <% } %>
                </td>
                <td><%= r.getRequestDate() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="4" class="text-center text-danger">No borrow history found.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div class="text-center mt-3">
        <a href="UserDashboardServlet" class="btn btn-secondary">⬅ Back to Dashboard</a>
    </div>
</div>
</body>
</html>
