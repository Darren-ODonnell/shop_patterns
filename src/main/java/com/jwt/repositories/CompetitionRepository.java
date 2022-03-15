package com.jwt.repositories;

import com.jwt.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findById(Long id);
    Competition findByName(String name);
    boolean existsByName(String name);
}