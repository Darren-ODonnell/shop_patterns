package com.jwt.models;


/**
 * @author Darren O'Donnell
 */
public class FirstnameModel {

    private String firstname;
    private String firstnameIrish;

    public String getFirstnameIrish() {
        return firstnameIrish;
    }
    public void setFirstnameIrish(String firstnameIrish) {
        this.firstnameIrish = firstnameIrish;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Firstname translateModelToFirstname(){
        Firstname firstname = new Firstname();
        firstname.setFirstname(this.firstname);
        firstname.setFirstnameIrish(this.firstnameIrish);
        return firstname;
    }

}
