package com.igor.wishlistapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "WIS_CONTENT")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_IN_ID")
    private Integer id;

    @Column(name = "CON_ST_TITLE")
    private String title;

    @Column(name = "CON_ST_DESCRIPTION")
    private String description;

    @Column(name = "CON_ST_CATEGORY")
    private String category;

    @Column(name = "CON_ST_SOURCE")
    private String source;

    @Column(name = "CON_IN_INTEREST")
    private Integer interest;

    @Column(name = "CON_DT_INCLUSION")
    private String inclusion;

    @Column(name = "PER_IN_ID")
    private Integer userId;

}
