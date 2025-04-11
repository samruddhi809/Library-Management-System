package com.library.servlets;

import com.library.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        BookDAO dao = new BookDAO();
        boolean success = dao.deleteBook(bookId);

        if (success) {
            response.sendRedirect("manage_books.jsp?success=Book+deleted+successfully");
        } else {
            response.sendRedirect("manage_books.jsp?error=Failed+to+delete+book");
        }
    }
}
