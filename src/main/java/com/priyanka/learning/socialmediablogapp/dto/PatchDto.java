package com.priyanka.learning.socialmediablogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatchDto {


    private String operation;
    private String key;
    private String value;
}
