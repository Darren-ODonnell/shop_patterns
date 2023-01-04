package com.jwt.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "fixtures", indexes = {
        @Index(name = "away_team_idx", columnList = "away_team_id"),
        @Index(name = "competition_idx", columnList = "competition_id"),
        @Index(name = "home_team_idx", columnList = "home_team_id")
})
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team_id")
    private Club homeTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away_team_id")
    private Club awayTeam;

    @Column(name = "fixture_date")
    private Date fixtureDate;

    // time is moved around as milliseconds between server and client, but stored in the db and display as actual time.
    // this annotation stops jpa from persisting this value to the db.
    @Transient
    private Long fixtureTime;

    @Column(name = "fixture_time")
    private Time sqlTime;

    @Column(name = "season")
    private Integer season;

    @Column(name = "round")
    private Integer round;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getSqlTime() {
        return sqlTime;
    }

    public void setSqlTime(Time sqlTime) {
        this.sqlTime = sqlTime;
    }

    public void setSqlTime(Long time) {
        this.sqlTime = new Time(time);
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Club getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Club homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Club getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Club awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getFixtureDate() {
        return fixtureDate;
    }

    public void setFixtureDate(Date fixtureDate) {
        this.fixtureDate = fixtureDate;
    }

    public Long getFixtureTime() {
        return fixtureTime;
    }

    public void setFixtureTime(Long fixtureTime) {
        this.fixtureTime = fixtureTime;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

}