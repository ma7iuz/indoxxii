package com.indoxxii.indoxxii.presist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idreview;

    @Column
    private Integer star;

    @Column
    private String review;

    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movies_idmovies",nullable = false)
    @JsonBackReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnoreProperties("orders")
    private User user;
}
