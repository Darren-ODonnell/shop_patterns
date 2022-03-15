package com.jwt.models;

public class CompetitionModel {

    private String name;
    private Integer season;

    public Integer getSeason() {
        return season;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Competition translateModelToCompetition(){
        Competition competition = new Competition();

        competition.setSeason(this.season);
        competition.setName(this.name);
        return competition;
    }

}