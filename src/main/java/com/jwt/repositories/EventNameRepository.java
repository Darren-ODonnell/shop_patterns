package com.jwt.repositories;

import com.jwt.models.EventName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventNameRepository extends JpaRepository<EventName, Long> {
    List<EventName> findAll();
    boolean existsByName(String name);
    boolean existsById(Long id);

    Optional<EventName> findByName(String name);
    Optional<EventName> findById(Long id);
    Optional<EventName> findByAbbrev(String name);
}