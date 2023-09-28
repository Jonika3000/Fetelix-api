package com.example.fetelix.controllers;
import com.example.fetelix.dto.actor.ActorItemDTO;
import com.example.fetelix.dto.actor.ActorPostDTO;
import com.example.fetelix.dto.actor.ActorUpdateDTO;
import com.example.fetelix.models.Actor;
import com.example.fetelix.repositories.ActorRepository;
import com.example.fetelix.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.fetelix.mappers.ActorMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/actor")
public class ActorController {
    private final ActorRepository repository;
    private final StorageService storageService;
    private final ActorMapper actorMapper;
    @GetMapping()
    public ResponseEntity<List<ActorItemDTO>> index() {
        var result = actorMapper.listActorsToItemDto(repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ActorItemDTO create(@ModelAttribute ActorPostDTO dto)
    {
        String fileName = storageService.saveImage(dto.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = dateFormat.parse(dto.getBirthday());
        } catch (ParseException e) {
        }

        var cat = Actor
                .builder()
                .name(dto.getName())
                .birthday(birthday)
                .birthLocation(dto.getPlace_of_birth())
                .image(fileName)
                .build();
        repository.save(cat);
        return actorMapper.ActorToItemDTO(cat);
    }
    @GetMapping("{id}")
    public ResponseEntity<ActorItemDTO> getActorById(@PathVariable int id) {
        Optional<Actor> catOpt = repository.findById((long) id);
        if(catOpt.isPresent())
        {
            var result = actorMapper.ActorToItemDTO(catOpt.get());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable int id) {
        Optional<Actor> catOpt = repository.findById((long) id);
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
    public ResponseEntity<ActorItemDTO> updateActorById(@PathVariable int id, @ModelAttribute ActorUpdateDTO dto) {
        Optional<Actor> catOpt = repository.findById((long) id);
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
            var result = actorMapper.ActorToItemDTO(cat);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
}
