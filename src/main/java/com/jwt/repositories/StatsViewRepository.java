package com.jwt.repositories;

import com.jwt.models.StatsView;
import com.jwt.models.StatsViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatsViewRepository extends JpaRepository<StatsView, StatsViewId> {
    @Query (value = "SELECT first_name, last_name, stat_name, fixture_date, count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND fixture_date= :fixtureDate " +
                    "GROUP by first_name, last_name",
            nativeQuery = true )
    List<Object[]> findByStatNameAndFixtureDate(String statname, Date fixtureDate);

    @Query (value = "SELECT first_name, last_name, stat_name, season, count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND season = :season " +
                    "GROUP BY fixture_date, first_name, last_name " +
                    "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object[]> findByStatNameAndSeason(String statname, int season);

    @Query (value =  "SELECT first_name, last_name,fixture_date,stat_name, count(*) FROM teamstats.stats_view " +
                    "where first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND fixture_date = :fixtureDate " +
                    "GROUP BY stat_name",
            nativeQuery = true)
    List<Object[]> findByFirstnameAndLastnameAndFixtureDate(String firstname, String lastname, Date fixtureDate);

    @Query (value = "SELECT first_name, last_name, stat_name, fixture_date, season, count(*) FROM teamstats.stats_view " +
                   "WHERE first_name = :firstname " +
                   "AND last_name = :lastname " +
                   "AND season = :season " +
                   "GROUP BY fixture_date, stat_name " +
                   "ORDER BY fixture_date, stat_name",
             nativeQuery = true)
    List<Object[]> findByFirstnameAndLastnameAndSeason(String firstname, String lastname, int season);

    @Query (value = "SELECT stat_name, fixture_date, count(*) FROM teamstats.stats_view " +
                    "WHERE fixture_date = :fixtureDate " +
                    "GROUP BY stat_name " +
                    "ORDER BY stat_name ",
            nativeQuery = true)
    List<Object[]> findByFixtureDate(Date fixtureDate);

    @Query (value = "SELECT stat_name, season, first_name, last_name, count(*) FROM teamstats.stats_view " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY stat_name, season ",
            nativeQuery = true)
    List<Object[]> findBySeason(int season);

    @Query (value = "SELECT fixture_date, count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, fixture_date " +
                    "ORDER BY fixture_date ",
            nativeQuery = true)
    List<Object[]> findDistinctByFixtureDate(String statName);

    @Query (value = "SELECT season, count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY season ",
            nativeQuery = true)
    List<Object[]> findDistinctBySeason(String statName);


    boolean existsById(StatsViewId id);
    Optional<StatsView> findById(StatsViewId id);
    List<StatsView> findAll();

    // ----------------------------------------------------------------------------------------------------
    /* Same endpoints as above except using a single class returned rather than one of multiple classes  */
    // ----------------------------------------------------------------------------------------------------

    // attempt to bring back all data and only use what is needed
    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY season ",
            nativeQuery = true)
    List<Object[]> countDistinctStatName2(String statName);


    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND fixture_date= :fixtureDate " +
                    "GROUP by first_name, last_name",
            nativeQuery = true )
    List<Object[]> countByStatNameAndFixtureDate2(String statname, Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND season = :season " +
                    "GROUP BY fixture_date, first_name, last_name " +
                    "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object[]> countByStatNameAndSeason2(String statname, int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "where first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND fixture_date = :fixtureDate " +
                    "GROUP BY stat_name",
            nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndFixtureDate2(String firstname, String lastname, Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND season = :season " +
                    "GROUP BY fixture_date, stat_name " +
                    "ORDER BY fixture_date, stat_name",
            nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndSeason2(String firstname, String lastname, int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE fixture_date = :fixtureDate " +
                    "GROUP BY stat_name " +
                    "ORDER BY stat_name ",
            nativeQuery = true)
    List<Object[]> countByFixtureDate2(Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY stat_name, season ",
            nativeQuery = true)
    List<Object[]> countBySeason2(int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, fixture_date " +
                    "ORDER BY fixture_date ",
            nativeQuery = true)
    List<Object[]> countDistinctByFixtureDate2(String statName);


}
