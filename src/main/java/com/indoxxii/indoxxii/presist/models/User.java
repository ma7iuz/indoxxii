package com.indoxxii.indoxxii.presist.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import lombok.Data;

@Entity
@Table(name = "users",indexes = {
    @Index(name="username_UNIQUE",columnList = "username",unique = true)
})
@Data
public class User {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private Date birthdate;

    @Column
    private String email;

    @OneToMany(targetEntity = Order.class)
    @JoinColumn(name = "users_id",referencedColumnName = "id")
    @JsonManagedReference
    @JsonIgnoreProperties("user")
    private Set<Order> orders;
}
