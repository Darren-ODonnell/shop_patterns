package com.jwt.models;

/**
 * @author Darren O'Donnell
 */
public class PlayerModel extends Fellowship {

    private String firstname;
    private String lastname;
    private String firstnameI;
    private String lastnameI;
    private Integer yob;
    private String address;
    private String email;
    private String phone;
    private String phoneIce;
    private boolean registered;
    private String grade;
    private String availability;

    private String fellowType;

    public PlayerModel() {}

    @Override
    public void setFellowType(String fellowType) {        this.fellowType = fellowType;    }
    public void setPhoneIce(String phoneIce) {
        this.phoneIce = phoneIce;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setYob(Integer yob) {
        this.yob = yob;
    }
    public void setLastnameI(String lastnameI) {
        this.lastnameI = lastnameI;
    }
    public void setFirstnameI(String firstnameI) {
        this.firstnameI = firstnameI;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setRegistered(boolean registered) { this.registered = registered; }
    public void setGrade(String grade) { this.grade = grade;  }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String getFellowType() {        return fellowType;    }
    public String getPhoneIce() {
        return phoneIce;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public Integer getYob() {
        return yob;
    }
    public String getLastnameI() {
        return lastnameI;
    }
    public String getFirstnameI() {
        return firstnameI;
    }
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
                return firstname;
    }
    public boolean isRegistered() { return registered; }
    public String getGrade() { return grade; }
    public String getAvailability() { return availability;  }

    public Fellowship translateModelToFellowship(){
        Fellowship fellow = new Fellowship();

        fellow.setFirstname(    this.firstname);
        fellow.setLastname(     this.lastname);
        fellow.setFirstnameI(   this.firstnameI);
        fellow.setLastnameI(    this.lastnameI);
        fellow.setEmail(        this.email);
        fellow.setAddress(      this.address);
        fellow.setPhone(        this.phone);
        fellow.setPhoneIce(     this.phoneIce);
        fellow.setYob(          this.yob);
        fellow.setRegistered(   this.registered);
        fellow.setAvailability( this.availability);
        fellow.setGrade(        this.grade);
        fellow.setFellowType(   "Player");

        return fellow;
    }

    // used in update operations

    public Fellowship translateModelToFellowship(Long id){
        Fellowship fellow = translateModelToFellowship();
        fellow.setId(id);
        return fellow;
    }
}
