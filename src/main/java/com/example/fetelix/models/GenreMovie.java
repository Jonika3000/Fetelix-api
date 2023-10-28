package com.example.fetelix.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_genre_movie")
public class GenreMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;
}
