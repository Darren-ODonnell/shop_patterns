package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "pitch_grid")
public class PitchGrid {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abbrev", nullable = false, length = 5)
    private String id;

    @Column(name = "name", length = 45)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}