package com.jwt.models;

public class FellowshipModel {

    private String firstname;
    private String lastname;
    private String firstnameI;
    private String lastnameI;
    private Integer yob;
    private String address;
    private String email;
    private String phone;
    private String phoneIce;
    private String availability;
    private String grade;
    private boolean registered;
    private Byte panelMember;
    private String fellowType;

    public FellowshipModel() {}

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

    public Integer getYob() {
        return yob;
    }

    public void setYob(Integer yob) {
        this.yob = yob;
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

    public String getPhoneIce() {
        return phoneIce;
    }

    public void setPhoneIce(String phoneIce) {
        this.phoneIce = phoneIce;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean getRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public Byte getPanelMember() {
        return panelMember;
    }

    public void setPanelMember(Byte panelMember) {
        this.panelMember = panelMember;
    }

    public String getFellowType() {
        return fellowType;
    }

    public void setFellowType(String fellowType) {
        this.fellowType = fellowType;
    }

    public Fellowship translateModelToFellowship(){
        Fellowship fellow = new Fellowship();

        fellow.setFirstname(this.firstname);
        fellow.setLastname(this.lastname);
        fellow.setFirstnameI(this.firstnameI);
        fellow.setLastnameI(this.lastnameI);
        fellow.setEmail(this.email);
        fellow.setAddress(this.address);
        fellow.setPhone(this.phone);
        fellow.setPhoneIce(this.phoneIce);
        fellow.setYob(this.yob);
        fellow.setRegistered(this.registered);
        fellow.setAvailability(this.availability);
        fellow.setGrade(this.grade);

        return fellow;
    }
    // used in update operations

    public Fellowship translateModelToFellowship(Long id){
        Fellowship fellow = translateModelToFellowship();
        fellow.setId(id);
        return fellow;
    }
    
}