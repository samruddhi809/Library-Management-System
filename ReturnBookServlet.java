
// ✅ ReturnBookServlet.java
package com.library.servlets;

import com.library.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = (int) request.getSession().getAttribute("userId");

        try (Connection con = DBConnection.getConnection()) {
            // Mark latest approved request as returned
            PreparedStatement updateRequest = con.prepareStatement("UPDATE BorrowRequests SET status='returned' WHERE user_id=? AND book_id=? AND status='approved' ORDER BY request_date DESC LIMIT 1");
            updateRequest.setInt(1, userId);
            updateRequest.setInt(2, bookId);
            int updated = updateRequest.executeUpdate();

            if (updated > 0) {
                // Add to transactions
                PreparedStatement insertTxn = con.prepareStatement("INSERT INTO Transactions (user_id, book_id, action, timestamp) VALUES (?, ?, 'returned', NOW())");
                insertTxn.setInt(1, userId);
                insertTxn.setInt(2, bookId);
                insertTxn.executeUpdate();

                // Increment book quantity
                PreparedStatement updateBook = con.prepareStatement("UPDATE Books SET quantity = quantity + 1 WHERE book_id = ?");
                updateBook.setInt(1, bookId);
                updateBook.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("UserDashboardServlet?message=Book returned successfully");
    }
}
