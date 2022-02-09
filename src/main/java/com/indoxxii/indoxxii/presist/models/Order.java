package com.indoxxii.indoxxii.presist.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idorders;

    @Column(unique = true)
    private String noinvoice;

    @Column
    private Date datecreated;

    @Column
    private Float totalprice;

    @Column(name = "paymentmethod")
    @Convert(converter = PaymentMethodAttributeConverter.class)
    private PaymentMethod paymentmethod;

    @Column(name = "orderstatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderstatus;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id",nullable = false)
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(
        name = "order_details",
        joinColumns = {@JoinColumn(name="orders_idorders")},
        inverseJoinColumns = {@JoinColumn(name="movies_idmovies")}
    )
    @JsonIgnoreProperties({"orders","reviews"})
    private Set<Movie> movies;

}
