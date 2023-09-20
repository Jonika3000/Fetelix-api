package com.example.fetelix.models;

import jakarta.persistence.*;

@Entity
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

    public int getId() {
        return this.id;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
