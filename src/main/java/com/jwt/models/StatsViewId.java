package com.jwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class StatsViewId implements Serializable {
    private static final long serialVersionUID = -3928530456159316243L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "time_occurred", nullable = false, precision = 4, scale = 2)
    private BigDecimal timeOccurred;

    @Convert(disableConversion = true)
    @Column(name = "fixture_date")
    private LocalDate fixtureDate;

    public BigDecimal getTimeOccurred() {
        return timeOccurred;
    }

    public LocalDate getFixtureDate() {
        return fixtureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatsViewId entity = (StatsViewId) o;
        return Objects.equals(this.fixtureDate, entity.fixtureDate) &&
                Objects.equals(this.timeOccurred, entity.timeOccurred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureDate, timeOccurred);
    }

}