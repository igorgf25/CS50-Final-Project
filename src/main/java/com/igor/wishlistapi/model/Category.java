package com.igor.wishlistapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "WIS_CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_IN_ID")
    private Integer id;

    @Column(name = "CAT_ST_NAME")
    private String name;

    @Column(name = "PER_IN_ID")
    private Integer userId;

}
