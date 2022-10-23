package com.jwt.models;

public class StatModel {


    private Fixture fixture;
    private Player player;
    private Boolean success;
    private Boolean half = false;
    private PitchGrid location;

    private StatName statName;

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getHalf() {
        return half;
    }

    public void setHalf(Boolean half) {
        this.half = half;
    }

    public PitchGrid getLocation() {
        return location;
    }

    public void setLocation(PitchGrid location) {
        this.location = location;
    }

    public StatName getStatName() {
        return statName;
    }

    public void setStatName(StatName statName) {
        this.statName = statName;
    }


    public Stat translateModelToStat(){
        Stat stat = new Stat();
        stat.setFixture(this.fixture);
        stat.setPlayer(this.player);
        stat.setSuccess(this.success);
        stat.setHalf(this.half);
        stat.setLocation(this.location);
        return stat;
    }

    // used in update operations
    public Stat translateModelToStat(StatId id){
        Stat stat = translateModelToStat();
        stat.setId(id);
        return stat;
    }
    
    
    
}