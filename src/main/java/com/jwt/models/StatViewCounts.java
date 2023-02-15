package com.jwt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.SVC;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;

@JsonIgnoreProperties
public class StatViewCounts extends StatsViewModel {

    private BigInteger count;

    public StatViewCounts(Object[] record) {

        this.setStatName((String) record[SVC.STAT_NAME]);
        this.setSuccess((Boolean) record[SVC.SUCCESS]);
        this.setHalf((Integer) record[SVC.HALF]);
        this.setTime_occurred((BigDecimal) record[SVC.TIME_OCCURRED]);
        this.setSeason((Integer) record[SVC.SEASON]);
        this.setFirstName((String) record[SVC.FIRST_NAME]);
        this.setLastName((String) record[SVC.LAST_NAME]);
        this.setHomeTeam((String) record[SVC.HOME_TEAM]);
        this.setAwayTeam((String) record[SVC.AWAY_TEAM]);
        this.setFixtureDate((Date) record[SVC.FIXTURE_DATE]);
        this.setLocation((String) record[SVC.LOCATION]);
        this.setCount(convertToBigInteger(record[SVC.STAT_COUNT]));
    }
    private BigInteger convertToBigInteger(Object object) {
        BigInteger value = null;
        if (object instanceof Float) {
            Float floatValue = (Float) object;
            BigDecimal decimal = BigDecimal.valueOf(Math.round(floatValue));
            value = decimal.toBigInteger();
        } else if (object instanceof Double) {
            Double doubleValue = (Double) object;
            BigDecimal decimalValue = BigDecimal.valueOf(Math.round(doubleValue));
            value =  decimalValue.toBigInteger();
        } else if (object instanceof BigDecimal) {
            value =  ((BigDecimal) object).round(new MathContext(0, RoundingMode.HALF_UP)).toBigInteger();
        } else if (object instanceof Integer) {
            value =  (BigInteger) object;
        }
        return value;
    }

    public BigInteger getCount() {
        return count;
    }
    public void setCount(BigInteger count) {        this.count = count;    }
}
