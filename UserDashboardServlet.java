package com.library.servlets;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.model.BorrowRequest;
import com.library.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ✅ Check if user is logged in
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("user_login.jsp"); // redirect if not logged in
            return;
        }

        int userId = (int) session.getAttribute("userId");

        try (Connection con = DBConnection.getConnection()) {
            BookDAO bookDAO = new BookDAO();
            String query = request.getParameter("query");
            List<Book> bookList;

            // ✅ Handle search functionality
            if (query != null && !query.trim().isEmpty()) {
                bookList = bookDAO.searchBooks(query); // filtered list
            } else {
                bookList = bookDAO.getAllBooks(); // full catalog
            }

            // ✅ Borrow history
            String historySql = "SELECT br.*, b.title FROM BorrowRequests br JOIN books b ON br.book_id = b.book_id WHERE user_id = ? ORDER BY request_date DESC";
            PreparedStatement ps = con.prepareStatement(historySql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<BorrowRequest> borrowHistory = new ArrayList<>();
            while (rs.next()) {
                BorrowRequest br = new BorrowRequest();
                br.setRequestId(rs.getInt("request_id"));
                br.setBookId(rs.getInt("book_id"));
                br.setBookTitle(rs.getString("title"));
                br.setStatus(rs.getString("status"));
                br.setRequestDate(rs.getTimestamp("request_date"));
                borrowHistory.add(br);
            }

            // ✅ Set attributes for JSP
            request.setAttribute("bookList", bookList);
            request.setAttribute("borrowHistory", borrowHistory);
            request.getRequestDispatcher("user_dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}