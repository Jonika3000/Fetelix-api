package com.example.fetelix.repositories;

import com.example.fetelix.models.ActorsMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsMoviesRepositrory  extends JpaRepository<ActorsMovies, Long> {
}
