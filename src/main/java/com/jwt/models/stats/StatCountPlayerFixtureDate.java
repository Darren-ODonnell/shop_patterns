package com.jwt.models.stats;

import java.math.BigInteger;
import java.util.Date;


public class StatCountPlayerFixtureDate extends StatCountFixtureDate {
    String firstname;
    String lastname;

    public StatCountPlayerFixtureDate(BigInteger count, String statname, String firstname, String lastname, Date fixtureDate) {
        super(count, statname, fixtureDate);
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public StatCountPlayerFixtureDate() {
        super(BigInteger.ZERO, "", null);
    }

    public StatCountPlayerFixtureDate(Object[] obj ) {
        // setup new object from that returned from the repo
        super((BigInteger) obj[4], (String) obj[3], (Date) obj[2]);
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