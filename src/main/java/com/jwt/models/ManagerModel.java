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

    public Manager translateModelToManager(){
        Manager manager = new Manager();

        manager.setFirstname(this.firstname);
        manager.setLastname(this.lastname);
        manager.setFirstnameI(this.firstnameI);
        manager.setLastnameI(this.lastnameI);
        manager.setEmail(this.email);
        manager.setAddress(this.address);
        manager.setPhone(this.phone);
        manager.setFellowType("Manager");

        return manager;
    }
    // used in update operations

    public Manager translateModelToFellowship(Long id){
        Manager manager = translateModelToManager();
        manager.setId(id);
        return manager;
    }
    
}