package com.igor.wishlistapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PersonRq {
    private String name;
    private String email;
    private String login;
    private String password;

}
