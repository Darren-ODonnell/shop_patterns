package com.jwt.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "stats_view", schema = "teamstats")
public class StatsView {
    @Id
    @jakarta.validation.constraints.Size(max = 36)
    @Column(name = "id", length = 36)
    private String id;

    @jakarta.validation.constraints.Size(max = 45)
    @jakarta.validation.constraints.NotNull
    @Column(name = "stat_name", nullable = false, length = 45)
    private String statName;

    @Column(name = "success")
    private Boolean success;

    @jakarta.validation.constraints.NotNull
    @Column(name = "half", nullable = false)
    private Boolean half = false;

    @jakarta.validation.constraints.NotNull
    @Column(name = "time_occurred", nullable = false, precision = 4, scale = 2)
    private BigDecimal timeOccurred;

    @Column(name = "season")
    private Integer season;

    @jakarta.validation.constraints.Size(max = 45)
    @jakarta.validation.constraints.NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @jakarta.validation.constraints.Size(max = 45)
    @jakarta.validation.constraints.NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @jakarta.validation.constraints.Size(max = 45)
    @Column(name = "home_team", length = 45)
    private String homeTeam;

    @jakarta.validation.constraints.Size(max = 45)
    @Column(name = "away_team", length = 45)
    private String awayTeam;

    @Column(name = "fixture_date")
    private String fixtureDate;

    public String getId() {
        return id;
    }

    public String getStatName() {
        return statName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Boolean getHalf() {
        return half;
    }

    public BigDecimal getTimeOccurred() {
        return timeOccurred;
    }

    public Integer getSeason() {
        return season;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getFixtureDate() {
        return fixtureDate;
    }

    protected StatsView() {
    }
}