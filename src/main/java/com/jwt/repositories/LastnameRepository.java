package com.jwt.repositories;

import com.jwt.models.Lastname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LastnameRepository extends JpaRepository<Lastname, Long> {
    Optional<Lastname> findById(Long id);
    Optional<List<Lastname>> findByLastname(String lastname);
    Optional<List<Lastname>> findByLastnameIrish(String lastnameIrish);

    boolean existsByLastname(String lastname);
}
