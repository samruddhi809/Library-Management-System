package com.library.model;

import java.sql.Timestamp;

public class BorrowRequest {
    private int requestId;
    private int userId;
    private int bookId;
    private String status;
    private Timestamp requestDate;

    // Extra fields for display purposes in admin panel
    private String username;
    private String bookTitle;

    // Constructors
    public BorrowRequest() {}

    public BorrowRequest(int requestId, int userId, int bookId, String status) {
        this.requestId = requestId;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
    }

    public BorrowRequest(int requestId, int userId, int bookId, String status, Timestamp requestDate) {
        this.requestId = requestId;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
        this.requestDate = requestDate;
    }

    // Getters and setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
