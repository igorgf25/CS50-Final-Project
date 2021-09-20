package com.igor.wishlistapi.controller.dto;

import com.igor.wishlistapi.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRs {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;

    public static PersonRs convert(Person p){
        PersonRs person = new PersonRs();
        person.setEmail(p.getEmail());
        person.setId(p.getId());
        person.setName(p.getName());
        person.setPassword(p.getPassword());
        person.setLogin(p.getLogin());
        return person;
    }


}
