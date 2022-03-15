package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "LAST_NAMES")
public class Lastname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "LAST_NAME", length = 45)
    private String lastname;

    @Column(name = "LAST_NAME_IRISH", length = 45)
    private String lastnameIrish;

    public String getLastnameIrish() {
        return lastnameIrish;
    }

    public void setLastnameIrish(String lastnameIrish) {
        this.lastnameIrish = lastnameIrish;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}