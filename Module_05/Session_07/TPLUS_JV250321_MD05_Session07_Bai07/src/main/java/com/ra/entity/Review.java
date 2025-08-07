package com.ra.entity;

import jakarta.validation.constraints.*;

public class Review {
    @NotBlank(message = "Tên không được để trống")
    private String username;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Min(value = 1, message = "Đánh giá tối thiểu là 1")
    @Max(value = 5, message = "Đánh giá tối đa là 5")
    private int rating;

    @NotBlank(message = "Bình luận không được để trống")
    private String comment;

    public Review() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
