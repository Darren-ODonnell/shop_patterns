package com.jwt.models;

import com.jwt.services.FixtureService;
import com.jwt.services.PitchGridService;
import com.jwt.services.PlayerService;
import com.jwt.services.StatNameService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class StatModel {
    FixtureService fixtureService;
    PlayerService playerService;
    PitchGridService pitchGridService;
    StatNameService statNameService;



    private Long fixtureId;
    private Long playerId;
    private Boolean success;
    private Integer half;
    private String locationId;
    private String statNameId;
    private BigDecimal timeOccurred;

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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

    public Stat translateModelToStat(){
        Stat stat = new Stat();
        stat.setFixture(fixtureService.getById(fixtureId));
        stat.setPlayer(playerService.findById(playerId));
        stat.setSuccess(this.success);
        stat.setHalf(this.half);
        PitchGrid location = pitchGridService.findById(locationId);
        stat.setLocation(location);
        stat.setStatName(statNameService.findById(statNameId));

        return stat;
    }

    // used in update operations
    public Stat translateModelToStat( StatId id){
        Stat stat = translateModelToStat();
        stat.setId(id);
        return stat;
    }


}