package com.example.fetelix.dto.movie;

import com.example.fetelix.models.Director;
import lombok.Data;

import java.util.Date;

@Data
public class MovieItemDto {
    private int id;
    private String title;
    private String image;
    private String country;
    private String description;
    private String releaseDate;
    private int time;
    private int directorId;
    private String slug;
    private String videoPath;
}
