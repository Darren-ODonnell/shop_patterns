package com.jwt.repositories;

import com.jwt.models.PitchGrid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PitchGridRepository extends JpaRepository<PitchGrid, Long> {
    List<PitchGrid> findAll();
    Optional<PitchGrid> findById(String id);
    Optional<PitchGrid> findByName(String name);
    void deleteById(String id);

    boolean existsById(String id);

}