package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "event_names")
public class EventName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "abbrev", nullable = false, length = 10)
    private String abbrev;

    public Long getId() {           return id;    }
    public void setId(Long id) {            this.id = id;    }

    public String getAbbrev() {     return abbrev;    }
    public void setAbbrev(String abbrev) {  this.abbrev = abbrev;    }

    public String getName() {       return name;    }
    public void setName(String name) {      this.name = name;    }

}