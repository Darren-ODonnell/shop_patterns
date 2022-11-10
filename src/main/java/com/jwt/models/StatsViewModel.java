package com.jwt.models;

import java.math.BigDecimal;
import java.sql.Date;

public class StatsViewModel {

    @jakarta.validation.constraints.NotNull
    private String statName;
    @jakarta.validation.constraints.NotNull
    private Boolean success;
    @jakarta.validation.constraints.NotNull
    private Integer half;
    @jakarta.validation.constraints.NotNull
    private Integer season;
    @jakarta.validation.constraints.NotNull
    private String firstName;
    @jakarta.validation.constraints.NotNull
    private String lastName;
    @jakarta.validation.constraints.NotNull
    private String homeTeam;
    @jakarta.validation.constraints.NotNull
    private String awayTeam;
    @jakarta.validation.constraints.NotNull
    private String location;
    @jakarta.validation.constraints.NotNull
    private Date fixtureDate;
    @jakarta.validation.constraints.NotNull
    private BigDecimal time_occurred;

    protected StatsViewModel() {
    }

    public String getStatName() {
        return statName;
    }
    public Boolean getSuccess() {
        return success;
    }
    public Integer getHalf() {
        return half;
    }
    public Integer getSeason()   {
        return season;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName()  {
        return lastName;
    }
    public String getHomeTeam()  {
        return homeTeam;
    }
    public String getAwayTeam()  {
        return awayTeam;
    }
    public String getLocation()  {
        return location;
    }
    public Date getFixtureDate() {          return fixtureDate;    }
    public BigDecimal getTime_occurred() {  return time_occurred;    }

    public void setStatName(String statName) {        this.statName = statName;     }
    public void setSuccess(Boolean success) {         this.success = success;       }
    public void setHalf(Integer half) {               this.half = half;             }
    public void setSeason(Integer season) {           this.season = season;         }
    public void setFirstName(String firstName) {      this.firstName = firstName;   }
    public void setLastName(String lastName) {        this.lastName = lastName;     }
    public void setHomeTeam(String homeTeam) {        this.homeTeam = homeTeam;     }
    public void setAwayTeam(String awayTeam) {        this.awayTeam = awayTeam;     }
    public void setFixtureDate(Date fixtureDate) {    this.fixtureDate = fixtureDate; }
    public void setTime_occurred(BigDecimal time_occurred) { this.time_occurred = time_occurred;}
    public void setLocation(String location) {        this.location = location;     }

}