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

    public StatCountFixtureDate(Object[] obj) {
        super((BigInteger) obj[2], (String) obj[0] );
        this.fixtureDate = (Date) obj[1];
    }

    public Date getFixtureDate() {
        return fixtureDate;
    }
}
