package com.jwt.models;

public class LastnameModel {

    private String lastname;
    private String lastnameIrish;

    public String getLastnameIrish() {
        return lastnameIrish;
    }
    public void setLastnameIrish(String lastnameIrish) {
        this.lastnameIrish = lastnameIrish;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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