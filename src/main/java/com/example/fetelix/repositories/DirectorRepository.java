package com.example.fetelix.repositories;

import com.example.fetelix.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository  extends JpaRepository<Director, Long> {
}
