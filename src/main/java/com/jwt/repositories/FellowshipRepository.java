package com.jwt.repositories;

import com.jwt.models.Fellowship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FellowshipRepository extends JpaRepository<Fellowship, Long> {
    List<Fellowship> findAll();
    Optional<Fellowship> findById(Long id);
    Optional<Fellowship> findByFirstnameAndLastname(String firstname, String lastname);
    Optional<List<Fellowship>> findByLastname(String lastname);
    Optional<List<Fellowship>> findByFirstname(String firstname);
    boolean existsByFirstnameAndLastname(String firstname, String lastname);
    Optional<Fellowship> findByEmail(String email);
    Optional<Fellowship> findByEmailAndFellowType(String email, String manager);
    Optional<Fellowship> findByFirstnameAndLastnameAndFellowType(String firstname, String lastname, String manager);
}
