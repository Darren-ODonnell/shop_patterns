package com.jwt.repositories;

import com.jwt.models.Club;
import com.jwt.models.Competition;
import com.jwt.models.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    Optional<Fixture> findById(Long id);
    List<Fixture> findAll();
    List<Fixture> findByAwayTeamId(Long id);
    List<Fixture> findByHomeTeamId(Long id);
    Fixture findFirstByAwayTeamIdOrHomeTeamIdAndFixtureDateGreaterThanOrderByFixtureDate(Long id1, Long id2, Date today);

    Fixture findByCompetitionIdAndHomeTeamIdAndAwayTeamIdAndFixtureDateAndSeason(Long compId, Long homeId, Long awayId, Date fixtureDate, int season);
    boolean existsByAwayTeamId(Long id);
    boolean existsByHomeTeamId(Long id);
    boolean existsFirstByAwayTeamIdOrHomeTeamIdAndFixtureDateGreaterThanOrderByFixtureDate(Long id1, Long id2, Date today);
    boolean existsByHomeTeamAndAwayTeamAndCompetitionAndSeason(Club home, Club away, Competition comp, int season);





//    Fixture findByCompetitionAndHomeTeamAndAwayTeamAndFixtureDateAndSeason(Competition competition, Club home, Club away, Date date, int season);
    Fixture findByCompetitionAndHomeTeamAndAwayTeam(Competition competition, Club home, Club away);
    boolean existsByCompetitionAndHomeTeamAndAwayTeam(Competition competition, Club home, Club away);
}