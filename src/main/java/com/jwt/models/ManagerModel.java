package com.jwt.models;

public class ManagerModel {

    private String firstname;
    private String lastname;
    private String firstnameI;
    private String lastnameI;
    private String address;
    private String email;
    private String phone;
    private String fellowType;

    public ManagerModel() {}

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstnameI() {
        return firstnameI;
    }
    public void setFirstnameI(String firstnameI) {
        this.firstnameI = firstnameI;
    }
    public String getLastnameI() {
        return lastnameI;
    }
    public void setLastnameI(String lastnameI) {
        this.lastnameI = lastnameI;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFellowType() {
        return fellowType;
    }
    public void setFellowType(String fellowType) {
        this.fellowType = fellowType;
    }

    public Fellowship translateModelToFellowship(){
        Fellowship fellow = new Fellowship();

        fellow.setFirstname(    this.firstname);
        fellow.setLastname(     this.lastname);
        fellow.setFirstnameI(   this.firstnameI);
        fellow.setLastnameI(    this.lastnameI);
        fellow.setEmail(        this.email);
        fellow.setAddress(      this.address);
        fellow.setPhone(        this.phone);

        fellow.setFellowType(   "Manager");

        return fellow;
    }


    // used in update operations

    public Fellowship translateModelToFellowship(Long id){
        Fellowship fellow = translateModelToFellowship();
        fellow.setId(id);
        return fellow;
    }
    
}