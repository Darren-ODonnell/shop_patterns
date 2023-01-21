package com.jwt.repositories;

// identify the repo entries for basic crud to begin with.

import com.jwt.models.Stat;
import com.jwt.models.StatId;
import com.jwt.models.StatName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatRepository extends JpaRepository<Stat, StatId> {
    boolean existsById(StatId id);
    void deleteById(StatId id);
    Optional<Stat> findByStatName(StatName statName);
    boolean existsByStatName(StatName statName);
    Optional<Stat> findById(StatId id);
}
