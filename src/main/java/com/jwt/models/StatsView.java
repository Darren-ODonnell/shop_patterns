package com.jwt.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "stats_view", schema = "teamstats")
public class StatsView {
    @EmbeddedId
    private StatsViewId id;

    @jakarta.validation.constraints.Size(max = 45)
    @jakarta.validation.constraints.NotNull
    @Column(name = "stat_name", nullable = false, length = 45)
    private String statName;

    @Column(name = "success")
    private Boolean success;

    @jakarta.validation.constraints.NotNull
    @Column(name = "half", nullable = false)
    private Integer half;

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

    public StatsViewId getId() {
        return id;
    }

    public void setId(StatsViewId id) {
        this.id = id;
    }

    public String getStatName() {
        return statName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getHalf() {
        return half;
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

    public LocalDate getFixtureDtae() {
        return id.getFixtureDate();
    }
    public BigDecimal getTimeOccurred() {
        return id.getTimeOccurred();
    }

    protected StatsView() {
    }
}