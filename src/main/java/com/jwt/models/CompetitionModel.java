package com.jwt.models;

import lombok.Data;

//@Data
public class CompetitionModel {

    public String name;
    public String irishName;
    public String grade;
    public Integer season;

    public void setName(String name) {
        this.name = name;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public void setIrishName(String irishName) {        this.irishName = irishName;    }
    public void setGrade() {        this.grade = grade;    }

    public String getIrishName() {
        return this.irishName;
    }
    public String getName() {
        return name;
    }
    public Integer getSeason() {
        return season;
    }
    public String getGrade() {
        return grade;
    }


    public Competition translateModelToCompetition(){
        Competition competition = new Competition();
        competition.setSeason(this.season);
        competition.setName(this.name);
        competition.setIrishName(this.irishName);
        competition.setGrade(this.grade);
        return competition;
    }

    // used in update operations
    public Competition translateModelToCompetition(Long id){
        Competition competition = translateModelToCompetition();
        competition.setId(id);
        return competition;
    }

}