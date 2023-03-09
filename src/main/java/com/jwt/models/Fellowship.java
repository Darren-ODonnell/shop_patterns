package com.jwt.models;

import com.jwt.security.User;

import javax.persistence.*;

@Entity
@Table(name = "fellowship")
public class Fellowship {
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

    @Column(name = "yob")
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
    private Integer registered;

    @Column(name = "panel_member")
    private Byte panelMember;

//    @OneToOne
//    @JoinColumn(name = "email", referencedColumnName = "email", insertable=false, updatable=false)
//    private User user;



    @Column(name = "fellow_type", length = 45)
    private String fellowType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getRegistered() {
        return registered;
    }

    public void setRegistered(Integer registered) {
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
//    public User getUser() {        return user;    }
//    public void setUser(User user) {        this.user = user;    }
}