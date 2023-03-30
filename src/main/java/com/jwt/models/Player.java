package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastname;

    @Column(name = "first_name_i", length = 45)
    private String firstnameI;

    @Column(name = "last_name_i", length = 45)
    private String lastnameI;

    @Column(name = "yob", nullable = false)
    private Integer yob;

    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "phone_ice", length = 45)
    private String phoneIce;

    @Column(name = "availability", length = 45)
    private String availability;

    @Column(name = "grade", length = 45)
    private String grade;

    @Column(name = "registered")
    private boolean registered;

    @Column(name = "panel_member")
    private Byte panelMember;

    @Column(name = "fellow_type", length = 45)
    private String fellowType;

    public boolean isRegistered() {        return registered;    }
    public String getFellowType() {        return fellowType;    }
    public void setFellowType(String fellowType) {        this.fellowType = fellowType;    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
    public String getFirstnameI() {
        return firstnameI;
    }
    public void setFirstnameI(String firstNameI) {
        this.firstnameI = firstNameI;
    }
    public String getLastnameI() {
        return lastnameI;
    }
    public void setLastnameI(String lastNameI) {
        this.lastnameI = lastNameI;
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
}