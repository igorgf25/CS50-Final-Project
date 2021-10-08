package com.igor.wishlistapi.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRq {
    private String name;
    private Integer userId;
}
