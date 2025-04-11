package com.library.servlets;

import com.library.dao.BookDAO;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int year = Integer.parseInt(request.getParameter("publicationYear"));

        Book book = new Book(bookId, title, author, isbn, quantity, year);
        BookDAO dao = new BookDAO();
        boolean success = dao.updateBook(book);

        if (success) {
            response.sendRedirect("manage_books.jsp?success=Book+updated+successfully");
        } else {
            response.sendRedirect("manage_books.jsp?error=Failed+to+update+book");
        }
    }
}
