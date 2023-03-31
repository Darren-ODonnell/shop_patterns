package com.jwt.models;

import com.jwt.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class StatModel {

    private Long fixtureId;
    private Long fellowshipId;
    private Boolean success;
    private Integer half;
    private String locationId;
    private String statNameId;
    private BigDecimal timeOccurred;

    public StatModel() {}

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Long getPlayerId() {
        return fellowshipId;
    }

    public void setPlayerId(Long playerId) {
        this.fellowshipId = playerId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getHalf() {
        return half;
    }

    public void setHalf(Integer half) {
        this.half = half;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStatNameId() {
        return statNameId;
    }

    public void setStatNameId(String statNameId) {
        this.statNameId = statNameId;
    }

    public BigDecimal getTimeOccurred() {
        return timeOccurred;
    }

    public void setTimeOccurred(BigDecimal timeOccurred) {
        this.timeOccurred = timeOccurred;
    }

    public Stat translateModelToStat(FixtureService fixtureService, PlayerService playerService, FellowshipService fellowshipService, PitchGridService pitchGridService, StatNameService statNameService){
        Stat stat = new Stat();
        stat.setFixture(fixtureService.getById(fixtureId));
        stat.setFellowship(fellowshipService.findById(fellowshipId));
        stat.setPlayer(playerService.findById(fellowshipId));
        stat.setSuccess(this.success);
        stat.setHalf(this.half);
        PitchGrid location = pitchGridService.findById(locationId);
        stat.setLocation(location);
        stat.setStatName(statNameService.findById(statNameId));

        return stat;
    }

    // used in update operations
    public Stat translateModelToStat(FixtureService fixtureService, PlayerService playerService, FellowshipService fellowshipService, PitchGridService pitchGridService, StatNameService statNameService, StatId id){
        Stat stat = translateModelToStat( fixtureService, playerService, fellowshipService,  pitchGridService,  statNameService);
        stat.setId(id);
        return stat;
    }


}