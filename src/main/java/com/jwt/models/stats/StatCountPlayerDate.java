package com.jwt.models.stats;

import java.math.BigInteger;
import java.sql.Date;



public class StatCountPlayerDate extends StatCountFixtureDate {
    String firstname;
    String lastname;

    public StatCountPlayerDate(Object[] obj ) {
        // setup new object from that returned from the repo
        super((BigInteger) obj[4], (String) obj[2], (Date) obj[3]);
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