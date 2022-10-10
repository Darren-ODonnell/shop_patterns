package com.jwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class StatId implements Serializable {
    private static final long serialVersionUID = -1478233472034346934L;
    @Column(name = "fixture_id", nullable = false)
    private Long fixtureId;

    @Column(name = "time_occurred", nullable = false, precision = 4, scale = 2)
    private BigDecimal timeOccurred;

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public BigDecimal getTimeOccurred() {
        return timeOccurred;
    }

    public void setTimeOccurred(BigDecimal timeOccurred) {
        this.timeOccurred = timeOccurred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatId entity = (StatId) o;
        return Objects.equals(this.fixtureId, entity.fixtureId) &&
                Objects.equals(this.timeOccurred, entity.timeOccurred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, timeOccurred);
    }

}