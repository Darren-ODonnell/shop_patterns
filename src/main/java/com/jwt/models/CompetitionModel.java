package com.jwt.models;

import lombok.Data;

@Data
public class CompetitionModel {

    public String name;
    public Integer season;

    public void setName(String name) {
        this.name = name;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }
    public Integer getSeason() {
        return season;
    }

    public Competition translateModelToCompetition(){
        Competition competition = new Competition();
        competition.setSeason(this.season);
        competition.setName(this.name);
        return competition;
    }

    // used in update operations
    public Competition translateModelToCompetition(Long id){
        Competition competition = translateModelToCompetition();
        competition.setId(id);
        return competition;
    }

}