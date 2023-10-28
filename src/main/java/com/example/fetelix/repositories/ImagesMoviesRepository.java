package com.example.fetelix.repositories;

import com.example.fetelix.models.ImagesMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesMoviesRepository extends JpaRepository<ImagesMovie, Long> {
}
