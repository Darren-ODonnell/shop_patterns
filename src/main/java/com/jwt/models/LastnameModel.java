package com.jwt.models;

import java.io.Serializable;

public class LastnameModel implements Serializable {

    private String lastname;
    private String lastnameIrish;

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