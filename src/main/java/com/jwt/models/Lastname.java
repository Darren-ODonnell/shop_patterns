package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "last_names")
public class Lastname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_name", length = 45)
    private String lastname;

    @Column(name = "last_name_irish", length = 45)
    private String lastnameIrish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getLastnameIrish() {
        return lastnameIrish;
    }

    public void setLastnameIrish(String lastNameIrish) {
        this.lastnameIrish = lastNameIrish;
    }

}