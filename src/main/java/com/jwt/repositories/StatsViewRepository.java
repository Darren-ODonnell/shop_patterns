package com.jwt.repositories;

// identify the repo entries for basic crud to begin with.

import com.jwt.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface StatsViewRepository extends JpaRepository<StatsView, String> {
    @Query (value =
            "SELECT first_name, last_name, stat_name, fixture_date, count(*) " +
            "FROM teamstats.stats_view " +
            "WHERE stat_name = :statname AND fixture_date= :fixtureDate " +
            "GROUP by first_name, last_name",
            nativeQuery = true )
    List<Object> findByStatNameAndFixtureDate(String statname, String fixtureDate);

    @Query ( value =
            "SELECT first_name, last_name, stat_name, season, count(*) " +
            "FROM teamstats.stats_view " +
            "WHERE stat_name = :statname AND season = :season " +
            "GROUP BY fixture_date, first_name, last_name " +
            "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object> findByStatNameAndSeason(String statname, int season);

    List<StatsView> findAll();
    boolean existsById(String id);
    Optional<StatsView> findById(String id);


}
