package com.jwt.models;

import javax.persistence.Column;


public class PitchGridModel {

    @Column(name = "name", length = 45)
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public PitchGrid translateModelToPitchGrid(){
        PitchGrid pitchGrid = new PitchGrid();

        pitchGrid.setName(this.name);

        return pitchGrid;
    }

    public PitchGrid translateModelToPitchGrid(String id){
        PitchGrid pitchGrid = translateModelToPitchGrid();

        pitchGrid.setId(id);

        return pitchGrid;
    }

}