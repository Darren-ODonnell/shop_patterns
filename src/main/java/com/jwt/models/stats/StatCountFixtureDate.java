package com.jwt.models.stats;

import java.math.BigInteger;
import java.util.Date;

public class StatCountFixtureDate extends Stats{
    Date fixtureDate;

    public StatCountFixtureDate() {
        super(BigInteger.ZERO,"");
    }

    public StatCountFixtureDate(BigInteger count, String statname, Date fixtureDate) {
        super(count, statname);
        this.fixtureDate = fixtureDate;
    }

    public StatCountFixtureDate(BigInteger count, String statname) {
        super(count, statname);
    }

    public Date getFixtureDate() {
        return fixtureDate;
    }
}
