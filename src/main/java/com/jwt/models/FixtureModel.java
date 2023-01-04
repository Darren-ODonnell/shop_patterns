package com.jwt.models;

import com.jwt.repositories.ClubRepository;
import com.jwt.repositories.CompetitionRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public class FixtureModel {
    private Long competitionId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Date fixtureDate;
    private Long fixtureTime;
    private Integer season;
    private Integer round;

    public void setRound(Integer round) {
        this.round = round;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public void setFixtureDate(Date fixtureDate) { this.fixtureDate = fixtureDate;    }
    public void setFixtureTime(Long fixtureTime) { this.fixtureTime = fixtureTime;    }
    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }
    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getRound() {
        return round;
    }
    public Integer getSeason() {
        return season;
    }
    public Date getFixtureDate() { return fixtureDate;    }
    public Long getFixtureTime() { return fixtureTime;    }
    public Long getAwayTeamId() {
        return awayTeamId;
    }
    public Long getHomeTeamId() {
        return homeTeamId;
    }
    public Long getCompetitionId() {
        return competitionId;
    }

    public Fixture translateModelToFixture(CompetitionRepository competitionRepository, ClubRepository clubRepository) {
        Fixture fixture = new Fixture();

        fixture.setCompetition(competitionRepository.getById(this.competitionId));
        Optional<Club> club = clubRepository.findById(this.homeTeamId);
        fixture.setHomeTeam(club.orElse(new Club()));
        club = clubRepository.findById(this.awayTeamId);
        fixture.setAwayTeam(club.orElse(new Club()));
        fixture.setFixtureDate(this.fixtureDate);
        fixture.setFixtureTime(this.fixtureTime);
        fixture.setSqlTime(new Time(this.fixtureTime));
        fixture.setSeason(this.season);
        fixture.setRound(this.round);

        return fixture;
    }
    // used in update operations

    public Fixture translateModelToFixture(CompetitionRepository competitionRepository, ClubRepository clubRepository, Long id) {
        Fixture fixture = translateModelToFixture(competitionRepository, clubRepository);
        fixture.setId(id);
        return fixture;
    }
}