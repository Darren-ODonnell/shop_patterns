package com.jwt.models;

import com.jwt.repositories.FixtureRepository;
import com.jwt.repositories.PitchGridRepository;
import com.jwt.repositories.PlayerRepository;
import com.jwt.repositories.StatNameRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class StatModel {

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

    public Stat translateModelToStat(FixtureRepository fixtureRepository, PlayerRepository playerRepository,
                                     PitchGridRepository pitchGridRepository, StatNameRepository statNameRepository){
        Stat stat = new Stat();
        stat.setFixture(fixtureRepository.getById(fixtureId));
        stat.setPlayer(playerRepository.getById(playerId));
        stat.setSuccess(this.success);
        stat.setHalf(this.half);
        Optional<PitchGrid> location = pitchGridRepository.findById(locationId);
        stat.setLocation(location.orElse(new PitchGrid()));
        stat.setStatName(statNameRepository.getById(statNameId));

        return stat;
    }

    // used in update operations
    public Stat translateModelToStat(FixtureRepository fixtureRepository, PlayerRepository playerRepository,
                                     PitchGridRepository pitchGridRepository, StatNameRepository statNameRepository, StatId id){
        Stat stat = translateModelToStat(fixtureRepository, playerRepository, pitchGridRepository, statNameRepository);
        stat.setId(id);
        return stat;
    }


}