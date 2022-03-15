package com.jwt.repositories;

import com.jwt.models.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<Stat, Long> {
}
