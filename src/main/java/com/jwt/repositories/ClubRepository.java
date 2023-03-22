package com.jwt.repositories;

import com.jwt.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findAll();
    Optional<Club> findById(Long id);
    Optional<Club> findByName(String name);
    boolean existsByName(String name);
    Optional<Club> getByName(String name);
}