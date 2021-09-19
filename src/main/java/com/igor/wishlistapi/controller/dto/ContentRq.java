package com.igor.wishlistapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ContentRq {
    private String title;
    private String description;
    private String category;
    private String source;
    private Integer interest;
    private Integer userId;
}
