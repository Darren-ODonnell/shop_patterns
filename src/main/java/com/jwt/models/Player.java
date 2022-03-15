package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstname;

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    private String lastname;

    @Column(name = "FIRST_NAME_I", length = 45)
    private String firstnameI;

    @Column(name = "LAST_NAME_I", length = 45)
    private String lastnameI;

    @Column(name = "YOB", nullable = false)
    private Integer yob;

    @Column(name = "ADDRESS", length = 128)
    private String address;

    @Column(name = "EMAIL", length = 45)
    private String email;

    @Column(name = "PHONE", length = 45)
    private String phone;

    @Column(name = "PHONE_ICE", length = 45)
    private String phoneIce;

    @Column(name = "REGISTERED")
    private boolean registered;

    @Column(name = "GRADE", length = 45)
    private String grade;

    @Column(name = "AVAILABILITY", length = 45)
    private String availability;

    public String getPhoneIce() {
        return phoneIce;
    }
    public void setPhoneIce(String phoneIce) {
        this.phoneIce = phoneIce;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getYob() {
        return yob;
    }
    public void setYob(Integer yob) {
        this.yob = yob;
    }
    public String getLastnameI() {
        return lastnameI;
    }
    public void setLastnameI(String lastnameI) {
        this.lastnameI = lastnameI;
    }
    public String getFirstnameI() {
        return firstnameI;
    }
    public void setFirstnameI(String firstnameI) {
        this.firstnameI = firstnameI;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRegistered() { return registered; }
    public void setRegistered(boolean registered) { this.registered = registered; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade;  }
    public String getAvailability() { return availability;  }
    public void setAvailability(String availability) { this.availability = availability; }
}