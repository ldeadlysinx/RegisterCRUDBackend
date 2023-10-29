package com.register.registertest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "index_user_table",indexes = @Index(name = "idx__USERNAME_NAME", columnList = "USERNAME,NAME"))
public class indexUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="USERNAME",nullable = false, unique = true)
    private String username;


    @Column(name= "NAME",nullable = false, unique = true)
    private String name;
}
