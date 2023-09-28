package com.example.fetelix.mappers;

import com.example.fetelix.models.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre GenreToItemDTO(Genre genre);
    List<Genre> listGenresToItemDto(List<Genre> list);
}