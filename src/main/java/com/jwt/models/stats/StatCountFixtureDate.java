package com.jwt.models.stats;

import java.math.BigInteger;
import java.sql.Date;


public class StatCountFixtureDate extends Stats{
    Date fixtureDate;


    public StatCountFixtureDate(BigInteger count, String statname, Date fixtureDate) {
        super(count, statname);
        this.fixtureDate = fixtureDate;
    }


    public StatCountFixtureDate(Object[] obj) {
        super((BigInteger) obj[2], (String) obj[0] );
        this.fixtureDate = (Date) obj[1];
    }

    public Date getFixtureDate() {
        return fixtureDate;
    }
}
