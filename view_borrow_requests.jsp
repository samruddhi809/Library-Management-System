<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.library.model.BorrowRequest" %>
<%
    List<BorrowRequest> borrowRequests = (List<BorrowRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Borrow Requests</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 30px;
        }
        .container {
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>📥 Borrow Requests</h2>
    <%
        if (borrowRequests != null && !borrowRequests.isEmpty()) {
    %>
    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>Request ID</th>
                <th>User</th>
                <th>Book</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% for (BorrowRequest r : borrowRequests) { %>
            <tr>
                <td><%= r.getRequestId() %></td>
                <td><%= r.getUsername() %></td>
                <td><%= r.getBookTitle() %></td>
                <td><%= r.getStatus() %></td>
                <td>
                    <form action="ApproveRejectRequestServlet" method="post" style="display:inline;">
                        <input type="hidden" name="requestId" value="<%= r.getRequestId() %>"/>
                        <input type="hidden" name="bookId" value="<%= r.getBookId() %>"/>
                        <button type="submit" name="action" value="approve" class="btn btn-success btn-sm">Approve</button>
                        <button type="submit" name="action" value="reject" class="btn btn-danger btn-sm">Reject</button>
                    </form>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-warning text-center">No borrow requests found.</div>
    <% } %>

    <div class="text-center mt-4">
        <a href="admin_dashboard.jsp" class="btn btn-secondary">⬅ Back to Dashboard</a>
    </div>
</div>
</body>
</html>
