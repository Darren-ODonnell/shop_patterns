package com.jwt.models.stats;

import java.math.BigInteger;


public class StatCountPlayerSeason extends StatCountSeason {
    String firstname;
    String lastname;

    public StatCountPlayerSeason(BigInteger count, String statname, String firstname, String lastname, int season) {
        super(count, statname, season);
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public StatCountPlayerSeason() {
        super(BigInteger.ZERO, "", 0);
    }

    public StatCountPlayerSeason(Object[] obj ) {
        // setup new object from that returned from the repo
        super((BigInteger) obj[5], (String) obj[2], (int) obj[4]);
        firstname = (String) obj[0];
        lastname = (String) obj[1];
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}