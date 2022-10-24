package com.jwt.models.stats;

import java.math.BigInteger;
import java.util.Date;

public class StatCountSeason extends Stats{
    int season;
    String firstname;
    String lastname;

    public StatCountSeason(BigInteger count, String statname, int season) {
        super(count, statname);
        this.season = season;
    }

    public StatCountSeason(Object[] obj ) {
        // setup new object from that returned from the repo
        super((BigInteger) obj[4], (String) obj[2]);
        this.season = (int) obj[3];
        this.firstname = (String) obj[0];
        this.lastname = (String) obj[1];
    }


    public int getSeason() {
        return season;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
