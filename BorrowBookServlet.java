// ✅ BorrowBookServlet.java (User sends borrow request)
package com.library.servlets;

import com.library.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO BorrowRequests (user_id, book_id, status) VALUES (?, ?, 'pending')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();

            response.sendRedirect("UserDashboardServlet?message=Request sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UserDashboardServlet?message=Error sending request");
        }
    }
}
