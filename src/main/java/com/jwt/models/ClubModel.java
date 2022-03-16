package com.jwt.models;

public class ClubModel {

    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String pitches;
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

    public Club translateModelToClub(){
        Club club = new Club();

        club.setName(this.name);
        club.setContactName(this.contactName);
        club.setContactEmail(this.contactEmail);
        club.setContactPhone(this.contactPhone);
        club.setPitches(this.pitches);
        club.setColours(this.colours);

        return club;
    }
    // used in update operations

    public Club translateModelToClub(Long id){
        Club club = translateModelToClub();
        club.setId(id);
        return club;
    }
}