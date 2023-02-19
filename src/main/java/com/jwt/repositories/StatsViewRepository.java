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

    boolean existsById(StatsViewId id);
    Optional<StatsView> findById(StatsViewId id);
    List<StatsView> findAll();

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



    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
           ", AVG(stat_count) AS count FROM " +
               "( SELECT stat_name, COUNT(*) AS stat_count, success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location " +
                "FROM teamstats.stats_view " +
                "WHERE away_team = :clubName OR home_team =:clubName " +
                "GROUP BY stat_name, fixture_date) MyQuery " +
           "GROUP BY stat_name ",
            nativeQuery = true)
    List<Object[]> averageScoreByOpposition(String clubName);

    @Query (value = "SELECT stat_name,success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location" +
            ", AVG(stat_count) AS count FROM " +
            "( SELECT stat_name, COUNT(*) AS stat_count, success,half,season,time_occurred,first_name, last_name, home_team, away_team, fixture_date, location " +
            "FROM teamstats.stats_view " +
            "WHERE (away_team = :clubName OR home_team =:clubName) " +
            "AND (away_team = :opposition OR home_team =:opposition) " +
            "GROUP BY stat_name, fixture_date) MyQuery " +
            "GROUP BY stat_name ",
            nativeQuery = true)
    List<Object[]> averageByStatNameByOpposition(String clubName, String opposition);





}
