package com.jwt.enums;

import java.util.HashMap;
import java.util.Map;

// Player availability
// county players are not available for league games - but are for Championship games

public enum Availability {

    COUNTY_SENIOR("CS"),
    COUNTY_INTER("CI"),
    COUNTY_JUNIOR("CJ"),
    COUNTY_MINOR("CM"),
    INJURED("INJ"),
    SICK("S"),
    AWAY("A"),
    YES("Y"),
    NO("N"),
    ;

    // pickup full text from abbreviation
    private static final Map<String, Availability> lookup = new HashMap<>();

    static {
        for (Availability a : Availability.values()) {
            lookup.put(a.getAbbreviation(), a);
        }
    }

    private final String abbreviation;

    Availability(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Availability get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
