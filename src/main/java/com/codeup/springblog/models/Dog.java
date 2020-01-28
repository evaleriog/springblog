package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED", nullable = false)
    private int id;

    @Column(columnDefinition = "TINYINT(11) UNSIGNED", nullable = false, unique = true)
    private int age;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "CHAR(2) DEFAULT 'XX'")
    private String reside_state;
}
