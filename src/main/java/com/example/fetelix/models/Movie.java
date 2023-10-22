package com.example.fetelix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title", length = 250, nullable = false)
    private String title;
    @Column(name = "image", length = 250, nullable = false)
    private String image;
    @Column(name = "country", length = 250, nullable = false)
    private String country;
    @Column(name = "description", length = 4000)
    private String description;
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
}