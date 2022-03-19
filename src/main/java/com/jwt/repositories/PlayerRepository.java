package com.jwt.repositories;

import com.jwt.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAll();
    Optional<Player> findById(Long id);
    Optional<Player> findByFirstnameAndLastname(String firstname, String lastname);
    Optional<List<Player>> findByLastname(String lastname);
    Optional<List<Player>> findByFirstname(String firstname);

    boolean existsByFirstnameAndLastname(String firstname, String lastname);
}
