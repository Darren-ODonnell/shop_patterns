package com.jwt.models;

/**
 * @author Darren O'Donnell
 */
public class PlayerModel {

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

    public Player translateModelToPlayer(){
        Player player = new Player();

        player.setFirstname(this.firstname);
        player.setLastname(this.lastname);
        player.setFirstnameI(this.firstnameI);
        player.setLastnameI(this.lastnameI);
        player.setEmail(this.email);
        player.setAddress(this.address);
        player.setPhone(this.phone);
        player.setPhoneIce(this.phoneIce);
        player.setYob(this.yob);
        player.setRegistered(this.registered);
        player.setAvailability(this.availability);
        player.setGrade(this.grade);

        return player;
    }
    // used in update operations

    public Player translateModelToPlayer(Long id){
        Player player = translateModelToPlayer();
        player.setId(id);
        return player;
    }
}
