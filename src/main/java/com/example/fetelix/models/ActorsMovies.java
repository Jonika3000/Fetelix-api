package com.example.fetelix.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_actors_movies")
public class ActorsMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "actor")
    private Actor actor;
    @ManyToOne
    @JoinColumn(name = "movie")
    private Movie movie;

    public int getId() {
        return this.id;
    }

    public Actor getActor() {
        return this.actor;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
