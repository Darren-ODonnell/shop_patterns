package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "stats", indexes = {
        @Index(name = "player_id", columnList = "player_id"),
        @Index(name = "stats_ibfk5_idx", columnList = "stat_name"),
        @Index(name = "location_id", columnList = "location_id")
})
public class Stat {
    @EmbeddedId
    private StatId id;

    @MapsId("fixtureId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fixture_id", nullable = false)
    private Fixture fixture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "half", nullable = false)
    private Integer half;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private PitchGrid location;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stat_name", nullable = false)
    private StatName statName;

    public StatId getId() {
        return id;
    }

    public void setId(StatId id) {
        this.id = id;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getHalf() {
        return half;
    }

    public void setHalf(Integer half) {
        this.half = half;
    }

    public PitchGrid getLocation() {
        return location;
    }

    public void setLocation(PitchGrid location) {
        this.location = location;
    }

    public StatName getStatName() {
        return statName;
    }

    public void setStatName(StatName statName) {
        this.statName = statName;
    }

}