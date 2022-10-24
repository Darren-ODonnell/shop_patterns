package com.jwt.models.stats;

import java.math.BigInteger;

public class Stats {
    BigInteger count;
    String statname;

    public Stats(BigInteger count, String statname) {
        this.count = count;
        this.statname = statname;
    }

    public BigInteger getCount() {
        return count;
    }
    public String getStatname() {
        return statname;
    }
}
