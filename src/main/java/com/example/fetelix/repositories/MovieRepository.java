package com.example.fetelix.repositories;

import com.example.fetelix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository  extends JpaRepository<Movie, Long> {
}
