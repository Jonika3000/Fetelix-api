package com.example.fetelix.mappers;

import com.example.fetelix.dto.movie.MovieItemDto;
import com.example.fetelix.models.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieItemDto MovieToItemDTO(Movie movie);
    List<MovieItemDto> listMovieToItemDto(List<Movie> list);
}
