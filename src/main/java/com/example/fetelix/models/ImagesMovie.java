package com.example.fetelix.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
}
