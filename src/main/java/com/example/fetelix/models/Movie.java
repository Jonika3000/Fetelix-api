package com.example.fetelix.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title", length = 250, nullable = false)
    private String title;
    @Column(name = "image", length = 250, nullable = false)
    private String image;
    @Column(name = "description", length = 4000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "release_date", columnDefinition = "DATE")
    private Date releaseDate;
    @Column(name = "time")
    private int time;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @Column(name = "slug")
    private String slug;
    @Column(name = "video_path")
    private String videoPath;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }

    public Country getCountry() {
        return this.country;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public int getTime() {
        return this.time;
    }

    public Director getDirector() {
        return this.director;
    }

    public String getSlug() {
        return this.slug;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}