package com.example.fetelix.mappers;

import com.example.fetelix.dto.genre.GenreDto;
import com.example.fetelix.models.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto GenreToItemDTO(Genre genre);
    List<GenreDto> listGenresToItemDto(List<Genre> list);
}