package com.priyanka.learning.socialmediablogapp.dto;

import lombok.Data;
import lombok.Getter;

@Data

public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
