package com.example.fetelix.controllers;

import com.example.fetelix.mappers.GenreMapper;
import com.example.fetelix.models.Genre;
import com.example.fetelix.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/genre")
public class GenreController {
    private final GenreRepository repository;
    private final GenreMapper genreMapper;

    @GetMapping()
    public ResponseEntity<List<Genre>> index() {
        var result = genreMapper.listGenresToItemDto(repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Genre create(@ModelAttribute Genre dto) {
        var cat = Genre
                .builder()
                .name(dto.getName())
                .build();
        repository.save(cat);
        return genreMapper.GenreToItemDTO(cat);
    }
    @GetMapping("{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int id) {
        Optional<Genre> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var result = genreMapper.GenreToItemDTO(catOpt.get());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable int id) {
        Optional<Genre> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var cat = catOpt.get();
            repository.delete(cat);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value="{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Genre> updateGenreById(@PathVariable int id, @ModelAttribute Genre dto) {
        Optional<Genre> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var cat = catOpt.get();

            cat.setName(dto.getName());
            repository.save(cat);
            var result = genreMapper.GenreToItemDTO(cat);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
}