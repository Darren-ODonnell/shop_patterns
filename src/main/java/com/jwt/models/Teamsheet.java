package com.jwt.models;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author Darren O'Donnell
 */

@Entity
@Table(name = "TEAMSHEETS")
public class Teamsheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID", nullable = false)
    private Integer id;
    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstname;
    @Column(name = "LAST_NAME", nullable = false)
    private Integer lastname;
    @Column(name = "FIRST_NAME_I", length = 45)
    private String firstnameI;
    @Column(name = "LAST_NAME_I", length = 45)
    private String lastnameI;
    @Column(name = "DOB", nullable = false)
    private Instant dob;
    @Column(name = "ADDRESS", length = 128)
    private String address;
    @Column(name = "EMAIL", length = 45)
    private String email;
    @Column(name = "PHONE", length = 45)
    private String phone;
    @Column(name = "PHONE_ICE", length = 45)
    private String phoneIce;
    @Column(name = "REGISTERED", length = 45)
    private String registered;
    @Column(name = "GRADE", length = 45)
    private String grade;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getRegistered() {
        return registered;
    }
    public void setRegistered(String registered) {
        this.registered = registered;
    }
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
    public Instant getDob() {
        return dob;
    }
    public void setDob(Instant dob) {
        this.dob = dob;
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
    public Integer getLastname() {
        return lastname;
    }
    public void setLastname(Integer lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}