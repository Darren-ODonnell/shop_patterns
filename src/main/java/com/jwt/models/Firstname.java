package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "first_names")
public class Firstname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", length = 45)
    private String firstname;

    @Column(name = "first_name_irish", length = 45)
    private String firstnameIrish;

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

    public String getFirstnameIrish() {
        return firstnameIrish;
    }

    public void setFirstnameIrish(String firstnameIrish) {
        this.firstnameIrish = firstnameIrish;
    }

}