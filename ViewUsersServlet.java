package com.library.servlets;

import com.library.DBConnection;
import com.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ViewUsersServlet")
public class ViewUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> userList = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("users", userList);
        request.getRequestDispatcher("view_users.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userIdStr = request.getParameter("deleteUserId");

        if (userIdStr != null) {
            try (Connection con = DBConnection.getConnection()) {
                int userId = Integer.parseInt(userIdStr);
                String deleteSql = "DELETE FROM users WHERE user_id=?";
                PreparedStatement stmt = con.prepareStatement(deleteSql);
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Refresh the page
        response.sendRedirect("ViewUsersServlet");
    }
}
