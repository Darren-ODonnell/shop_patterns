package com.jwt.repositories;

import com.jwt.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    boolean existsById(Long id);
    Optional<Event> findById(Long id);
    List<Event> findByFixtureId(Long id);
    List<Event> findByFixtureIdAndPlayerId(Long id1, Long id2);
}