package com.igor.wishlistapi.controller.dto;

import com.igor.wishlistapi.model.Person;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
