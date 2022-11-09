package com.jwt.models.stats;

import java.math.BigInteger;

public class StatCountSeason extends Stats{
    int season;
    String firstname;
    String lastname;

    public StatCountSeason(BigInteger count, String statname, int season) {
        super(count, statname);
        this.season = season;
    }
    public StatCountSeason() {
        super( BigInteger.ZERO, "");

    }

    public StatCountSeason(Object[] obj ) {
        // setup new object from that returned from the repo
        super((BigInteger) obj[4], (String) obj[0]);

        this.season = (int) obj[1];
        this.firstname = (String) obj[2];
        this.lastname = (String) obj[3];

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
