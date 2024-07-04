package com.interview.hotelbooking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "number")
    private int number;

    @Column(name = "price")
    private double price;

    @Column(name = "isBooking")
    private Boolean isBooking;

    @ManyToOne
    @JoinColumn(name = "fk_hotel_id")
    private Hotel hotel;
}
