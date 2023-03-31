package com.jwt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LastnameModel implements Serializable {
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("lastnameIrish")
    private String lastnameIrish;

    public LastnameModel() {
    }

    public LastnameModel(String lastname, String lastnameIrish) {
        this.lastname = lastname;
        this.lastnameIrish = lastnameIrish;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setLastnameIrish(String lastnameIrish) {
        this.lastnameIrish = lastnameIrish;
    }

    public String getLastname() {
        return lastname;
    }
    public String getLastnameIrish() {
        return lastnameIrish;
    }



    public Lastname translateModelToLastname(){
        Lastname lastname = new Lastname();
        lastname.setLastname(this.lastname);
        lastname.setLastnameIrish(this.lastnameIrish);
        return lastname;
    }
    // used in update operations

    public Lastname translateModelToLastname(Long id){
        Lastname lastname = translateModelToLastname();
        lastname.setId(id);
        return lastname;
    }
}