package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "irish_name", length = 45)
    private String irishName;

    @Column(name = "contact_name", length = 45)
    private String contactName;

    @Column(name = "contact_email", length = 45)
    private String contactEmail;

    @Column(name = "contact_phone", length = 45)
    private String contactPhone;

    @Column(name = "pitches", length = 128)
    private String pitches;

    @Column(name = "colours", length = 45)
    private String colours;

    @Column(name = "abbrev", length = 10)
    private String abbrev;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIrishName() {
        return irishName;
    }

    public void setIrishName(String irishName) {
        this.irishName = irishName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPitches() {
        return pitches;
    }

    public void setPitches(String pitches) {
        this.pitches = pitches;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
}