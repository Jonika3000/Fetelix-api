package com.example.fetelix.controllers;

import com.example.fetelix.dto.director.DirectorItemDTO;
import com.example.fetelix.dto.director.DirectorPostDto;
import com.example.fetelix.dto.director.DirectorUpdateDto;
import com.example.fetelix.mappers.DirectorMapper;
import com.example.fetelix.models.Director;
import com.example.fetelix.repositories.DirectorRepository;
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
@RequestMapping("api/director")
@SecurityRequirement(name="my-api")
public class DirectorController {
    private final DirectorRepository repository;
    private final StorageService storageService;
    private final DirectorMapper directorMapper;
    @GetMapping()
    public ResponseEntity<List<DirectorItemDTO>> index() {
        var result = directorMapper.listActorsToItemDto(repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DirectorItemDTO create(@ModelAttribute DirectorPostDto dto)
    {
        String fileName = storageService.saveImage(dto.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = dateFormat.parse(dto.getBirthday());
        } catch (ParseException e) {
        }

        var cat = Director
                .builder()
                .name(dto.getName())
                .birthday(birthday)
                .birthLocation(dto.getPlace_of_birth())
                .image(fileName)
                .build();
        repository.save(cat);
        return directorMapper.DirectorToItemDTO(cat);
    }
    @GetMapping("{id}")
    public ResponseEntity<DirectorItemDTO> getDirectorById(@PathVariable int id) {
        Optional<Director> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var result = directorMapper.DirectorToItemDTO(catOpt.get());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDirectorById(@PathVariable int id) {
        Optional<Director> catOpt = repository.findById((long) id);
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
    public ResponseEntity<DirectorItemDTO> updateDirectorById(@PathVariable int id, @ModelAttribute DirectorUpdateDto dto) {
        Optional<Director> catOpt = repository.findById((long) id);
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
            Date birthday = null;
            try {
                birthday = dateFormat.parse(dto.getBirthday());
            } catch (ParseException e) {
            }
            cat.setName(dto.getName());
            cat.setBirthday(birthday);
            repository.save(cat);
            var result = directorMapper.DirectorToItemDTO(cat);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
}
