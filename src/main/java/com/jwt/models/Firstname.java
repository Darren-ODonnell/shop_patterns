package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "FIRST_NAMES")
public class Firstname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", length = 45)
    private String firstname;

    @Column(name = "FIRST_NAME_IRISH", length = 45)
    private String firstnameIrish;

    public String getFirstnameIrish() {
        return firstnameIrish;
    }

    public void setFirstnameIrish(String firstnameIrish) {
        this.firstnameIrish = firstnameIrish;
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
}