package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "teamsheets", indexes = {
        @Index(name = "player_id", columnList = "player_id"),
        @Index(name = "position_id", columnList = "position_id")
})
public class Teamsheet {
    @EmbeddedId
    private TeamsheetId id;

    @MapsId("fixtureId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fixture_id", nullable = false)
    private Fixture fixture;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    public TeamsheetId getId() {
        return id;
    }

    public void setId(TeamsheetId id) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}