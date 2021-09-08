package com.igor.wishlistapi.model;


import javax.persistence.*;

@Entity
@Table(name = "WIS_PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_IN_ID")
    private Long id;

    @Column(name = "PER_ST_NAME")
    private String name;

    @Column(name = "PER_ST_EMAIL")
    private String email;

    @Column(name = "PER_ST_LOGIN")
    private String login;

    @Column(name = "PER_ST_PASSWORD")
    private String password;

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
