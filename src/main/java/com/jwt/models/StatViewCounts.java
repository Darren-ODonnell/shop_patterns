package com.jwt.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import java.math.BigInteger;

@Entity
@Immutable
public class StatViewCounts extends StatsView {

    private BigInteger count;

    public StatViewCounts() {

    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
