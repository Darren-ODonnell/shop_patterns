package com.jwt.repositories;

import com.jwt.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long id);
    Player findByFirstnameAndLastname(String firstname, String lastname);
    List<Player> findAll();
    List<Player> findByLastname(String lastname);
    List<Player> findByFirstname(String firstname);
    boolean existsByFirstnameAndLastname(String firstname, String lastname);
    void deleteById(Long id);
}
