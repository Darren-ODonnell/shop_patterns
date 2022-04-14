package com.jwt.models;

public class EventModel {

    private Fixture fixture;
    private PitchGrid pitchGrid;
    private Player player;
    private Integer time;
    private EventName eventName;

    public EventName getEventName() {        return eventName;    }
    public void setEventName(EventName eventName) {        this.eventName = eventName;    }

    public Integer getTime() {
        return this.time;
    }
    public void setTime(Integer time) {
        this.time = time;
    }

    public Player getPlayer() {
                                                        return this.player;
    }
    public void setPlayer(Player player) {              this.player = player;    }

    public PitchGrid getPitchGrid() {                   return this.pitchGrid;    }
    public void setPitchGrid(PitchGrid pitchGrid) {     this.pitchGrid = pitchGrid;    }

    public Fixture getFixture() {                       return fixture;    }
    public void setFixture(Fixture fixture) {           this.fixture = fixture;    }


    public Event translateModelToEvent(){
        Event event = new Event();

        event.setPlayer(this.player);
        event.setPitchGrid(this.pitchGrid);
        event.setFixture(this.fixture);
        event.setTime(this.time);
        event.setEventName(this.eventName);

        return event;
    }

    public Event translateModelToEvent(Long id){
        Event event = translateModelToEvent();

        event.setId(id);

        return event;
    }

}