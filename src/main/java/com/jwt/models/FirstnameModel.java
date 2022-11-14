package com.jwt.models;


/**
 * @author Darren O'Donnell
 */
public class FirstnameModel {

    private String firstname;
    private String firstnameIrish;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setFirstnameIrish(String firstnameIrish) {
        this.firstnameIrish = firstnameIrish;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getFirstnameIrish() {
        return firstnameIrish;
    }

    public Firstname translateModelToFirstname(){
        Firstname firstname = new Firstname();
        firstname.setFirstname(this.firstname);
        firstname.setFirstnameIrish(this.firstnameIrish);
        return firstname;
    }

    // used in update operations
    public Firstname translateModelToFirstname(Long id){
        Firstname firstname = translateModelToFirstname();
        firstname.setId(id);
        return firstname;
    }

}
