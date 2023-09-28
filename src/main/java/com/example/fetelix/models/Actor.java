package com.example.fetelix.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_actors")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday", columnDefinition = "DATE")
    private Date birthday;
    @Column(name = "image")
    private String image;
    @Column(name = "place_of_birth")
    private String birthLocation;
}
