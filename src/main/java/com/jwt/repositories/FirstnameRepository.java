package com.jwt.repositories;

import com.jwt.models.Firstname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FirstnameRepository extends JpaRepository<Firstname, Long> {
    Optional<Firstname> findById(Long id);
    Firstname findByFirstname(String firstname);
    List<Firstname> findByFirstnameIrish(String firstnameIrish);
    boolean existsByFirstname(String firstname);
    boolean existsByFirstnameIrish(String firstname);
}
