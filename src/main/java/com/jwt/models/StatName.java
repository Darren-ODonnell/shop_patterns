package com.jwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stat_names")
public class StatName {
    @Id
    @Column(name = "abbrev", nullable = false, length = 10)
    private String id;

    @Column(name = "name", nullable = false, length = 45)
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