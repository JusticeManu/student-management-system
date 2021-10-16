package com.example.studentmanagementsystem.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}
