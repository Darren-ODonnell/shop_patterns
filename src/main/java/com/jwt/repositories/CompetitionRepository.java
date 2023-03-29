package com.jwt.repositories;

import com.jwt.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findAll();
    Optional<Competition> findById(Long id);
    Optional<List<Competition>> findByName(String name);
    boolean existsByName(String name);
    Optional<List<Competition>> findBySeason(Integer season);

}