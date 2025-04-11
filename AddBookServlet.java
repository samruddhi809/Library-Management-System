// AddBookServlet.java
package com.library.servlets;

import com.library.dao.BookDAO;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int year = Integer.parseInt(request.getParameter("publicationYear"));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setQuantity(quantity);
        book.setPublicationYear(year);

        BookDAO dao = new BookDAO();
        boolean success = dao.addBook(book);

        if (success) {
            response.sendRedirect("manage_books.jsp?success=Book+added+successfully");
        } else {
            response.sendRedirect("manage_books.jsp?error=Failed+to+add+book");
        }
    }
}
