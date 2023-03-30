package com.jwt.models;

public class PositionModel {


    private Long id;
    private String name;
    private String abbrev;

    public String getAbbrev() {
        return abbrev;
    }
    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public Long getId() {        return id;    }
    public void setId(Long id) {  this.id = id;    }
    public String getName() {        return name;    }
    public void setName(String name) {
        this.name = name;
    }

    public Position translateModelToPosition() {
        Position position = new Position();
        position.setId(this.id);
        position.setName(this.name);
        position.setAbbrev(this.abbrev);

        return position;
    }

    public Position translateModelToPosition(Long id){
        Position position = translateModelToPosition();
        position.setId(id);
        return position;
    }

}