package com.jwt.repositories;

import com.jwt.models.Firstname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FirstnameRepository extends JpaRepository<Firstname, Long> {
    Optional<Firstname> findById(Long id);
    Optional<Firstname> findByFirstname(String firstname);
    Optional<List<Firstname>> findByFirstnameIrish(String firstnameIrish);

    boolean existsByFirstname(String firstname);

}
