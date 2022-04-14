package com.jwt.repositories;

import com.jwt.models.PitchGrid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PitchGridRepository extends JpaRepository<PitchGrid, Long> {
    List<PitchGrid> findAll();
    Optional<PitchGrid> findById(Long id);
    Optional<PitchGrid> findByName(String name);
    Optional<PitchGrid> findByAbbrev(String abbrev);
    boolean existsById(Long id);
    boolean existsByAbbrev(String abbrev);
}