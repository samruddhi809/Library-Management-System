// ✅ ApproveRejectRequestServlet.java
package com.library.servlets;

import com.library.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ApproveRejectRequestServlet")
public class ApproveRejectRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // approve or reject
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try (Connection con = DBConnection.getConnection()) {
            if ("approve".equalsIgnoreCase(action)) {
                // Update borrow request status
                String updateRequest = "UPDATE BorrowRequests SET status = 'approved' WHERE request_id = ?";
                PreparedStatement ps = con.prepareStatement(updateRequest);
                ps.setInt(1, requestId);
                ps.executeUpdate();

                // Decrease book quantity
                String updateBook = "UPDATE Books SET quantity = quantity - 1 WHERE book_id = ? AND quantity > 0";
                PreparedStatement ps2 = con.prepareStatement(updateBook);
                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                // Insert into Transactions
                String txn = "INSERT INTO Transactions (user_id, book_id, action, timestamp) " +
                             "SELECT user_id, book_id, 'borrowed', CURRENT_TIMESTAMP FROM BorrowRequests WHERE request_id = ?";
                PreparedStatement txnStmt = con.prepareStatement(txn);
                txnStmt.setInt(1, requestId);
                txnStmt.executeUpdate();

            } else if ("reject".equalsIgnoreCase(action)) {
                // Simply mark request as rejected
                String updateRequest = "UPDATE BorrowRequests SET status = 'rejected' WHERE request_id = ?";
                PreparedStatement ps = con.prepareStatement(updateRequest);
                ps.setInt(1, requestId);
                ps.executeUpdate();
            }

            response.sendRedirect("ViewBorrowRequestsServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}