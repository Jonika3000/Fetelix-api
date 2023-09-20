package com.example.fetelix.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_images_movie")
public class ImagesMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;
    @Column(name = "image_path")
    private String imagePath;

    public int getId() {
        return this.id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
