package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fixture_id")
    private Fixture fixture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_names_id")
    private EventName eventName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pitchgrid_id")
    private PitchGrid pitchGrid;

    @Column(name = "time")
    private Integer time;

    public Long getId() {               return id;    }
    public void setId(Long id) {                this.id = id;    }

    public Fixture getFixture() {       return fixture;    }
    public void setFixture(Fixture fixture) {   this.fixture = fixture;    }

    public Player getPlayer() {         return player;    }
    public void setPlayer(Player player) {      this.player = player;    }

    public Integer getTime() {          return time;    }
    public void setTime(Integer time) {         this.time = time;    }

    public EventName getEventNames() {  return eventName;    }
    public void setEventName(EventName eventNames) {        this.eventName = eventNames;    }

    public PitchGrid getPitchGrid() {   return pitchGrid;    }
    public void setPitchGrid(PitchGrid pitchGrid) {        this.pitchGrid = pitchGrid;    }
}