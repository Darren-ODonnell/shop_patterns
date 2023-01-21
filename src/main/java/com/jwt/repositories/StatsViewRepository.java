package com.jwt.repositories;

import com.jwt.models.StatsView;
import com.jwt.models.StatsViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<Object[]> countByStatNameAndFixtureDate(@Param("statname") String statname, @Param("fixtureDate") Date fixtureDate);

    @Query (value = "SELECT first_name, last_name, stat_name, season, count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND season = :season " +
                    "GROUP BY fixture_date, first_name, last_name " +
                    "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object[]> countByStatNameAndSeason(@Param("statname") String statname, @Param("season") int season);

    @Query (value = "SELECT first_name, last_name,fixture_date,stat_name, count(*) FROM teamstats.stats_view " +
                    "where first_name = :firstname AND last_name = :lastname " +
                    "AND fixture_date = :fixtureDate " +
                    "GROUP BY stat_name",
            nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndFixtureDate(@Param("firstname") String firstname, @Param("lastname")String lastname,@Param("fixtureDate") Date fixtureDate);

    @Query (value = "SELECT first_name, last_name, stat_name, fixture_date, season, count(*) FROM teamstats.stats_view " +
                   "WHERE first_name = :firstname AND last_name = :lastname AND season = :season " +
                   "GROUP BY fixture_date, stat_name " +
                   "ORDER BY fixture_date, stat_name",
             nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndSeason(@Param("firstname")String firstname, @Param("lastname")String lastname,@Param("season") int season);

    @Query (value = "SELECT stat_name, fixture_date, count(*) FROM teamstats.stats_view " +
                    "WHERE fixture_date = :fixtureDate " +
                    "GROUP BY stat_name " +
                    "ORDER BY stat_name ",
            nativeQuery = true)
    List<Object[]> countByFixtureDate(@Param("fixtureDate") Date fixtureDate);

    @Query (value = "SELECT first_name, last_name, stat_name, season,  count(*) FROM teamstats.stats_view " +
                    "GROUP BY stat_name, season, last_name, first_name " +
                    "ORDER BY stat_name, season, last_name, first_name ",
            nativeQuery = true)
    List<Object[]> countBySeason();

    @Query (value = "SELECT fixture_date, count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, fixture_date " +
                    "ORDER BY fixture_date ",
            nativeQuery = true)
    List<Object[]> countDistinctByFixtureDate(@Param("statName") String statName);

    @Query (value = "SELECT season, count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY season ",
            nativeQuery = true)
    List<Object[]> countDistinctBySeason(@Param("statName") String statName);


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
    List<Object[]> countDistinctStatName2(@Param("statName") String statName);


    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND fixture_date= :fixtureDate " +
                    "GROUP by first_name, last_name "+
                    "ORDER by first_name, last_name",
            nativeQuery = true )
    List<Object[]> countByStatNameAndFixtureDate2(@Param("statname")String statname,@Param("fixtureDate")Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE stat_name = :statname AND season = :season " +
                    "GROUP BY fixture_date, first_name, last_name " +
                    "ORDER BY last_name, first_name, fixture_date;",
            nativeQuery = true )
    List<Object[]> countByStatNameAndSeason2(@Param("statname")String statname, @Param("season")int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "where first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND fixture_date = :fixtureDate " +
                    "GROUP BY stat_name",
            nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndFixtureDate2(@Param("firstname")String firstname,@Param("lastname") String lastname, @Param("fixtureDate")Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE first_name = :firstname " +
                    "AND last_name = :lastname " +
                    "AND season = :season " +
                    "GROUP BY fixture_date, stat_name " +
                    "ORDER BY fixture_date, stat_name",
            nativeQuery = true)
    List<Object[]> countByFirstnameAndLastnameAndSeason2(@Param("firstname")String firstname,@Param("lastname") String lastname, @Param("season")int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "WHERE fixture_date = :fixtureDate " +
                    "GROUP BY stat_name " +
                    "ORDER BY stat_name ",
            nativeQuery = true)
    List<Object[]> countByFixtureDate2(@Param("fixtureDate")Date fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "GROUP BY stat_name, season " +
                    "ORDER BY stat_name, season ",
            nativeQuery = true)
    List<Object[]> countBySeason2(@Param("season")int season);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
                    ", count(*) FROM teamstats.stats_view " +
                    "where stat_name = :statName " +
                    "GROUP BY stat_name, fixture_date " +
                    "ORDER BY fixture_date ",
            nativeQuery = true)
    List<Object[]> countDistinctByFixtureDate2(@Param("statName")String statName);

    // ------------------------------------------------------------------------------------------------------

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "GROUP BY first_name, last_name, stat_name " +
            "ORDER BY first_name, last_name, stat_name ",
            nativeQuery = true)
    List<Object[]> countAllPlayerStat();

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where stat_name = :statName " +
            "GROUP BY first_name, last_name, fixture_date " +
            "ORDER BY first_name, last_name, fixture_date ",
            nativeQuery = true)
    List<Object[]> countAllPlayerFixtureByStatName(@Param("statName")String statName);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where fixture_date = :fixtureDate " +
            "GROUP BY first_name, last_name, stat_name " +
            "ORDER BY first_name, last_name, stat_name",
            nativeQuery = true)
    List<Object[]> countAllPlayerStatNameByFixtureDate(@Param("fixtureDate")String fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where first_name = :firstname " +
            "AND last_name = :lastname " +
            "GROUP BY stat_name, fixture_date " +
            "ORDER BY stat_name, fixture_date ",
            nativeQuery = true)
    List<Object[]> countAllStatNameFixtureByPlayer(@Param("firstname")String firstname, @Param("lastname")String lastname);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where fixture_date = :fixtureDate " +
            "AND stat_name = :statName " +
            "GROUP BY first_name, last_name " +
            "ORDER BY first_name, last_name",
            nativeQuery = true)
    List<Object[]> countAllPlayerByFixtureStatName(@Param("fixtureDate")String fixtureDate, @Param("statName")String statName);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where first_name = :firstname " +
            "AND last_name = :lastname " +
            "AND stat_name = :statName " +
            "GROUP BY fixture_date " +
            "ORDER BY fixture_date ",
            nativeQuery = true)
    List<Object[]> countAllFixtureByPlayerStatName(@Param("firstname")String firstname, @Param("lastname")String lastname, @Param("statName")String statName);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where first_name = :firstname " +
            "AND last_name = :lastname " +
            "AND fixture_date = :fixtureDate " +
            "GROUP BY stat_name " +
            "ORDER BY stat_name ",
            nativeQuery = true)
    List<Object[]> countAllStatsByPlayerFixtureDate(@Param("firstname")String firstname, @Param("lastname")String lastname, @Param("fixtureDate") String fixtureDate);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where first_name = :firstname " +
            "AND last_name = :lastname " +
            "AND fixture_date = :fixtureDate " +
            "AND stat_name = :statName ",
            nativeQuery = true)
    List<Object[]> countStat(@Param("firstname")String firstname, @Param("lastname")String lastname, @Param("fixtureDate")String fixtureDate, @Param("statName")String statName);


    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", count(*) FROM teamstats.stats_view " +
            "where fixture_date = :fixtureDate " +
            "GROUP BY stat_name, success " +
            "ORDER BY stat_name, success",
            nativeQuery = true)
    List<Object[]> countAllPlayerStatNameByFixtureDateGroupSuccess(@Param("fixtureDate")String fixtureDate);

}
