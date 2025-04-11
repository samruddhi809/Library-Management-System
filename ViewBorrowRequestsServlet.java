package com.library.servlets;

import com.library.DBConnection;
import com.library.model.BorrowRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ViewBorrowRequestsServlet")
public class ViewBorrowRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BorrowRequest> requests = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            // ✅ Fetch all requests with user and book info
            String sql = "SELECT r.request_id, r.user_id, u.username, b.title AS book_title, r.book_id, r.status, r.request_date " +
                         "FROM BorrowRequests r " +
                         "JOIN users u ON r.user_id = u.user_id " +
                         "JOIN books b ON r.book_id = b.book_id " +
                         "ORDER BY r.request_date DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BorrowRequest br = new BorrowRequest();
                br.setRequestId(rs.getInt("request_id"));
                br.setUserId(rs.getInt("user_id"));
                br.setUsername(rs.getString("username"));
                br.setBookId(rs.getInt("book_id"));
                br.setBookTitle(rs.getString("book_title"));
                br.setStatus(rs.getString("status"));
                br.setRequestDate(rs.getTimestamp("request_date"));
                requests.add(br);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("view_borrow_requests.jsp").forward(request, response);
    }
}
