package com.jwt.repositories;

import com.jwt.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findById(Long id);
    Club findClubById(Long id);
    Club findByName(String name);
    List<Club> findAll();
    boolean existsByName(String name);
}