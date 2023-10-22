package com.example.fetelix.controllers;

import com.example.fetelix.dto.actor.ActorItemDTO;
import com.example.fetelix.dto.actor.ActorUpdateDTO;
import com.example.fetelix.dto.movie.MovieItemDto;
import com.example.fetelix.dto.movie.MoviePostDto;
import com.example.fetelix.dto.movie.MovieUpdateDto;
import com.example.fetelix.mappers.DirectorMapper;
import com.example.fetelix.mappers.MovieMapper;
import com.example.fetelix.models.Actor;
import com.example.fetelix.models.Director;
import com.example.fetelix.models.Movie;
import com.example.fetelix.repositories.DirectorRepository;
import com.example.fetelix.repositories.MovieRepository;
import com.example.fetelix.storage.StorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/movie")
@SecurityRequirement(name="my-api")
public class MovieController {
    private final MovieRepository repository;
    private final DirectorRepository repositoryDirector;
    private final StorageService storageService;
    private final MovieMapper movieMapper;
    @GetMapping()
    public ResponseEntity<List<MovieItemDto>> index() {
        var result = movieMapper.listMovieToItemDto(repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MovieItemDto create(@ModelAttribute MoviePostDto dto)
    {
        String fileName = storageService.saveImage(dto.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Optional<Director> directorMovieCat = repositoryDirector.findById((long) dto.getDirectorId());
        Date releaseDate = null;
        try {
            releaseDate = dateFormat.parse(dto.getReleaseDate());
        } catch (ParseException e) {
        }

        var cat = Movie
                .builder()
                .title(dto.getTitle())
                .releaseDate(releaseDate)
                .country(dto.getCountry())
                .image(fileName)
                .description(dto.getDescription())
                .time(dto.getTime())
                .slug(dto.getSlug())
                .videoPath(dto.getVideoPath())
                .director(directorMovieCat.get())
                .build();
        repository.save(cat);
        return movieMapper.MovieToItemDTO(cat);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieItemDto> getMovieById(@PathVariable int id) {
        Optional<Movie> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var result = movieMapper.MovieToItemDTO(catOpt.get());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable int id) {
        Optional<Movie> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var cat = catOpt.get();
            if(cat.getImage()!=null) {
                storageService.removeFile(cat.getImage());
            }
            repository.delete(cat);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value="{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieItemDto> updateMovieById(@PathVariable int id, @ModelAttribute MovieUpdateDto dto) {
        Optional<Movie> catOpt = repository.findById((long) id);
        Optional<Director> directorMovieCat = repositoryDirector.findById((long) dto.getDirectorId());
        if(catOpt.isPresent())
        {
            var cat = catOpt.get();
            if(dto.getImage()!=null) {
                if (cat.getImage() != null) {
                    storageService.removeFile(cat.getImage());
                }
                String fileName = storageService.saveImage(dto.getImage());
                cat.setImage(fileName);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = null;
            try {
                releaseDate = dateFormat.parse(dto.getReleaseDate());
            } catch (ParseException e) {
            }
            cat.setTitle(dto.getTitle());
            cat.setReleaseDate(releaseDate);
            cat.setCountry(dto.getCountry());
            cat.setDescription(dto.getDescription());
            cat.setTime(dto.getTime());
            cat.setSlug(dto.getSlug());
            cat.setVideoPath(dto.getVideoPath());
            cat.setDirector(directorMovieCat.get());
            repository.save(cat);
            var result = movieMapper.MovieToItemDTO(cat);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
}
