package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class CommentDTO {
    private Long id;

    @NotEmpty
    private String message;

    @NotEmpty
    private String username;

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
