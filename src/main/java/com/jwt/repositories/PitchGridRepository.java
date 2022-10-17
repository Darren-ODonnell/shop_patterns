package com.jwt.repositories;

import com.jwt.models.PitchGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PitchGridRepository extends JpaRepository<PitchGrid, Long> {
    List<PitchGrid> findAll();
    Optional<PitchGrid> findById(String id);
    Optional<PitchGrid> findByName(String name);
    void deleteById(String id);

    boolean existsById(String id);

}