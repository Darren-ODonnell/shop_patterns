package com.jwt.models;

import com.jwt.repositories.ClubRepository;
import com.jwt.repositories.CompetitionRepository;
import com.jwt.services.ClubService;
import com.jwt.services.CompetitionService;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public class FixtureModel {
    private Long competitionId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Date fixtureDate;
    private Time fixtureTime;
    private Integer season;
    private Integer round;

    public void setRound(Integer round) {
        this.round = round;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public void setFixtureDate(Date fixtureDate) { this.fixtureDate = fixtureDate;    }
    public void setFixtureTime(Time fixtureTime) { this.fixtureTime = fixtureTime;    }
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
    public Time getFixtureTime() { return fixtureTime;    }
    public Long getAwayTeamId() {
        return awayTeamId;
    }
    public Long getHomeTeamId() {
        return homeTeamId;
    }
    public Long getCompetitionId() {
        return competitionId;
    }

    public Fixture translateModelToFixture(CompetitionService competitionService, ClubService clubService) {
        Fixture fixture = new Fixture();

        fixture.setCompetition(competitionService.findById(this.competitionId));
        Club club = clubService.findById(this.homeTeamId);
        fixture.setHomeTeam(club);
        club = clubService.findById(this.awayTeamId);
        fixture.setAwayTeam(club);
        fixture.setFixtureDate(this.fixtureDate);
        fixture.setFixtureTime(this.fixtureTime);
//        Time time = new Time(this.fixtureTime);
//        fixture.setSqlTime(time);
        fixture.setSeason(this.season);
        fixture.setRound(this.round);

        return fixture;
    }
    // used in update operations

    public Fixture translateModelToFixture(CompetitionService competitionService, ClubService clubService, Long id) {
        Fixture fixture = translateModelToFixture(competitionService, clubService);
        fixture.setId(id);
        return fixture;
    }
}