package com.example.fetelix.mappers;

import com.example.fetelix.dto.actor.ActorItemDTO;
import com.example.fetelix.models.Actor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorItemDTO ActorToItemDTO(Actor actor);
    List<ActorItemDTO> listActorsToItemDto(List<Actor> list);
}