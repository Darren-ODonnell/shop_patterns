package com.jwt.models;

import javax.persistence.Column;

public class StatNameModel {

    private String name;
    private String abbrev;

    public StatNameModel() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAbbrev() {
        return abbrev;
    }
    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public StatName translateModelToStatName(){

        StatName statName = new StatName();
        statName.setId(this.abbrev);
        statName.setName(this.name);
        return statName;
    }

    public StatName translateModelToStatName(String id){
        StatName statName = translateModelToStatName();
        statName.setId(id);
        return statName;
    }
    
    
    
    
    
}