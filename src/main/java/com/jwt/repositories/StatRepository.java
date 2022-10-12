package com.jwt.repositories;

// identify the repo entries for basic crud to begin with.

import com.jwt.models.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
