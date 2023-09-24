package com.example.fetelix.dto.actor;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ActorUpdateDTO {
    private String name;
    private String birthday;
    private String place_of_birth;
    private MultipartFile image;
}