package com.jwt.repositories;

// identify the repo entries for basic crud to begin with.

import com.jwt.models.*;
import com.jwt.services.StatsViewService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatsViewRepository extends JpaRepository<StatsView, StatsViewId> {

    @Query (value =
            "SELECT first_name, last_name, stat_name, fixture_date, count(*) " +
            "FROM teamstats.stats_view " +
            "WHERE stat_name = :statname AND fixture_date= :fixtureDate " +
            "GROUP by first_name, last_name",
            nativeQuery = true )
    List<Object> findByStatNameAndFixtureDate(String statname, Date fixtureDate);

    @Query ( value =
            "SELECT first_name, last_name, stat_name, season, count(*) " +
            "FROM teamstats.stats_view " +
            "WHERE stat_name = :statname AND season = :season " +
            "GROUP BY fixture_date, first_name, last_name " +
            "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object> findByStatNameAndSeason(String statname, int season);

    @Query(value =
            "SELECT Fixture_date,stat_name, count(*) FROM teamstats.stats_view " +
            "where first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND fixture_date = :fixtureDate " +
                    "GROUP BY stat_name",
            nativeQuery = true)
    List<Object> findByFirstnameAndLastnameAndFixtureDate(String firstname, String lastname, String statname, Date fixtureDate);

    List<StatsView> findAll();
    boolean existsById(StatsViewId id);
    Optional<StatsView> findById(StatsViewId id);


}
