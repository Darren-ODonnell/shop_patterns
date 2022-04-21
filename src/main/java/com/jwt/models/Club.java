package com.jwt.models;

import javax.persistence.*;

@Entity
@Table(name = "CLUBS")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 45)
    private String name;

    @Column(name = "CONTACT_NAME", length = 45)
    private String contactName;

    @Column(name = "CONTACT_EMAIL", length = 45)
    private String contactEmail;

    @Column(name = "CONTACT_PHONE", length = 45)
    private String contactPhone;

    @Column(name = "PITCHES", length = 128)
    private String pitches;

    @Column(name = "COLOURS", length = 45)
    private String colours;

    public String getColours() {
        return colours;
    }
    public void setColours(String colours) {
        this.colours = colours;
    }
    public String getPitches() {
        return pitches;
    }
    public void setPitches(String pitches) {
        this.pitches = pitches;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}