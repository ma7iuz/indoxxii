package com.indoxxii.indoxxii.presist.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idmovies;

    @Column
    private String title;

    @Column
    private String synopsis;

    @Column
    private String posterdirectory;

    @Column
    private Float price;

    @Column
    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(targetEntity = Review.class)
    @JoinColumn(name = "movies_idmovies", referencedColumnName = "idmovies")
    @JsonManagedReference
    @JsonIgnoreProperties("movie")
    private Set<Review> reviews;
}
