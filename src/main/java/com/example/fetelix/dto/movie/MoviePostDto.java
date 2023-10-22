package com.example.fetelix.dto.movie;

import com.example.fetelix.models.Director;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class MoviePostDto {
    private String title;
    private MultipartFile image;
    private String country;
    private String description;
    private String releaseDate;
    private int time;
    private int directorId;
    private String slug;
    private String videoPath;
}
