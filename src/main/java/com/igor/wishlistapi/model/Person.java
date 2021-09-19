package com.igor.wishlistapi.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "WIS_PERSON")
public class Person {

    public Person(Long id){
        setId(id);
    }
    public Person(){
    }


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
}
