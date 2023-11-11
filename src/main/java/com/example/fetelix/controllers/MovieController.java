package com.example.fetelix.controllers;

import com.example.fetelix.dto.movie.MovieItemDto;
import com.example.fetelix.dto.movie.MoviePostDto;
import com.example.fetelix.dto.movie.MovieUpdateDto;
import com.example.fetelix.mappers.MovieMapper;
import com.example.fetelix.models.*;
import com.example.fetelix.repositories.*;
import com.example.fetelix.storage.StorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
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
    private final ActorRepository actorRepository;
    private final DirectorRepository repositoryDirector;
    private final StorageService storageService;
    private final MovieMapper movieMapper;
    private final ActorsMoviesRepositrory actorsMoviesRepositrory;
    private final GenresMoviesRepository genresMoviesRepository;
    private final GenreRepository genreRepository;
    private final ImagesMoviesRepository imagesMoviesRepository;
    @GetMapping()
    public ResponseEntity<List<MovieItemDto>> index() {
        var result = movieMapper.listMovieToItemDto(repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MovieItemDto create(@ModelAttribute MoviePostDto dto)
    {
        String fileName = storageService.saveImage(dto.getImage());
        String movieName = storageService.saveVideo(dto.getVideoPath());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int d = dto.getDirectorId();
        Optional<Director> directorMovieCat = repositoryDirector.findById((long) dto.getDirectorId());
        Date releaseDate = null;
        try {
            releaseDate = dateFormat.parse(dto.getReleaseDate());
        } catch (ParseException e) {
        }
        ///2023-11-01T21:30:54.516+02:00  WARN 1576 --- [nio-8080-exec-8] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public com.example.fetelix.dto.movie.MovieItemDto com.example.fetelix.controllers.MovieController.create(com.example.fetelix.dto.movie.MoviePostDto): [Field error in object 'moviePostDto' on field 'videoPath': rejected value [org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@18dc6237]; codes [typeMismatch.moviePostDto.videoPath,typeMismatch.videoPath,typeMismatch.java.lang.String,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [moviePostDto.videoPath,videoPath]; arguments []; default message [videoPath]]; default message [Failed to convert property value of type 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile' to required type 'java.lang.String' for property 'videoPath'; Cannot convert value of type 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile' to required type 'java.lang.String' for property 'videoPath': no matching editors or conversion strategy found]] ]
        var cat = Movie
                .builder()
                .title(dto.getTitle())
                .releaseDate(releaseDate)
                .country(dto.getCountry())
                .image(fileName)
                .description(dto.getDescription())
                .time(dto.getTime())
                .slug(dto.getSlug())
                .videoPath(movieName)
                .director(directorMovieCat.get())
                .build();
        repository.save(cat);
        for (var id:dto.getActorsIds()) {
            ActorsMovies am = new ActorsMovies();
            am.setActor((actorRepository.findById((long)id)).get());
            am.setMovie(cat);
            actorsMoviesRepositrory.save(am);
        }
        for (var id:dto.getGenresIds()) {
            GenreMovie gm = new GenreMovie();
            gm.setMovie(cat);
            gm.setGenre((genreRepository.findById((long)id)).get());
            genresMoviesRepository.save(gm);
        }
        for (var image:dto.getImages()) {
            ImagesMovie im = new ImagesMovie();
            im.setMovie(cat);
            String nameImage = storageService.saveImage(image);
            im.setImagePath(nameImage);
            imagesMoviesRepository.save(im);
        }
        return movieMapper.MovieToItemDTO(cat);
    }

    @GetMapping("{slug}")
    public ResponseEntity<MovieItemDto> getMovieBySlug(@PathVariable String slug) {
            List<Movie> movies = repository.findAll();
            for (var movie:movies) {
                if (movie.getSlug().equals(slug)) {
                 var result = movieMapper.MovieToItemDTO(movie);
                 return ResponseEntity.ok().body(result);
             }
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
