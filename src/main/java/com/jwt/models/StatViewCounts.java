package com.jwt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.SVC;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

@JsonIgnoreProperties
public class StatViewCounts extends StatsViewModel {


    public StatViewCounts() {

    }
    public StatViewCounts(Object[] record) {

//        this.setStatsViewId( (String) record[ID]);
        this.setStatName( (String)          record[SVC.STAT_NAME]);
        this.setSuccess( (Boolean)          record[SVC.SUCCESS]);
        this.setHalf( (Integer)             record[SVC.HALF]);
        this.setTime_occurred( (BigDecimal) record[SVC.TIME_OCCURRED]);
        this.setSeason( (Integer)           record[SVC.SEASON]);
        this.setFirstName( (String)         record[SVC.FIRST_NAME]);
        this.setLastName( (String)          record[SVC.LAST_NAME]);
        this.setHomeTeam( (String)          record[SVC.HOME_TEAM]);
        this.setAwayTeam( (String)          record[SVC.AWAY_TEAM]);
        this.setFixtureDate( (Date)         record[SVC.FIXTURE_DATE]);
        this.setLocation( (String)          record[SVC.LOCATION]);
        this.setCount( (BigInteger)         record[SVC.STAT_COUNT]);

    }
    private BigInteger count;

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
