package com.example.fetelix.repositories;

import com.example.fetelix.models.GenreMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresMoviesRepository extends JpaRepository<GenreMovie, Long> {
}
