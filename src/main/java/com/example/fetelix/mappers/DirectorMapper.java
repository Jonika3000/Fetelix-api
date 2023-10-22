package com.example.fetelix.mappers;

import com.example.fetelix.dto.director.DirectorItemDTO;
import com.example.fetelix.models.Director;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorItemDTO DirectorToItemDTO(Director director);
    List<DirectorItemDTO> listActorsToItemDto(List<Director> list);
}
