package com.jwt.repositories;

import com.jwt.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAll();
    boolean existsById(String id);
    boolean existsByName(String name);
    Optional<Position> findByName(String name);
    Optional<Position> findById(String id);
    Optional<Position> findByAbbrev(String abbrev);
}