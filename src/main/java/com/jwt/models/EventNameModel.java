package com.jwt.models;

public class EventNameModel {

    private String name;
    private String abbrev;


    public String getAbbrev() {  return abbrev;    }
    public void setAbbrev(String abbrev) { this.abbrev = abbrev;    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public EventName translateModelToEventName(){
        EventName eventName = new EventName();
        eventName.setName(this.name);
        eventName.setAbbrev(this.abbrev);
        return eventName;
    }

    public EventName translateModelToEventName(Long id){
        EventName eventName = translateModelToEventName();
        eventName.setId(id);
        return eventName;
    }
}