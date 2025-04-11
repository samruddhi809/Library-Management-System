<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f5f7fa;
            font-family: 'Segoe UI', sans-serif;
        }
        .dashboard-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }
        .btn-lg {
            width: 100%;
            margin-bottom: 20px;
            font-size: 18px;
            padding: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="dashboard-container">
        <h2>📋 Admin Dashboard</h2>

        <a href="ViewUsersServlet" class="btn btn-primary btn-lg">👥 View Registered Users</a>

        <a href="manage_books.jsp" class="btn btn-success btn-lg">📚 Manage Books</a>

        <a href="ViewBorrowRequestsServlet" class="btn btn-warning btn-lg text-white">✅ Approve/Reject Borrow/Return Requests</a>

        <a href="ViewTransactionsServlet" class="btn btn-info btn-lg text-white">📜 View Transaction History</a>

        <a href="logout.jsp" class="btn btn-danger btn-lg">🚪 Logout</a>
    </div>
</div>
</body>
</html>
