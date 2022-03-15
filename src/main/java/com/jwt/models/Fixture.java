package com.jwt.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "FIXTURES")
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPETITION_ID")
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOME_TEAM_ID")
    private Club homeTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AWAY_TEAM_ID")
    private Club awayTeam;

    @Column(name = "FIXTURE_DATE")
    private Date fixtureDate;

    @Column(name = "FIXTURE_TIME")
    private Time fixtureTime;

    @Column(name = "SEASON")
    private Integer season;

    @Column(name = "ROUND")
    private Integer round;

    public Integer getRound() {
        return round;
    }
    public void setRound(Integer round) {
        this.round = round;
    }
    public Integer getSeason() {
        return season;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public Time getFixtureTime() {        return fixtureTime;    }
    public void setFixtureTime(Time fixtureTime) {
        this.fixtureTime = fixtureTime;
    }
    public Date getFixtureDate() {
        return fixtureDate;
    }
    public void setFixtureDate(Date fixtureDate) {
        this.fixtureDate = fixtureDate;
    }
    public Club getAwayTeam() {
        return awayTeam;
    }
    public void setAwayTeam(Club awayTeam) {
        this.awayTeam = awayTeam;
    }
    public Club getHomeTeam() {
        return homeTeam;
    }
    public void setHomeTeam(Club homeTeam) {
        this.homeTeam = homeTeam;
    }
    public Competition getCompetition() {
        return competition;
    }
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}