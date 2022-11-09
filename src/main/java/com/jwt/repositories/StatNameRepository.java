package com.jwt.repositories;

import com.jwt.models.StatName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatNameRepository extends JpaRepository<StatName, String> {
    List<StatName> findAll();
    Optional<StatName> findById(String id);
    Optional<StatName> findByName(String name);
    void deleteById(String id);
    boolean existsById(String id);

}