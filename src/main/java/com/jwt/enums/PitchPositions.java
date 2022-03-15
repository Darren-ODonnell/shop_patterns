package com.jwt.enums;

import java.util.HashMap;
import java.util.Map;

public enum PitchPositions {
    GOAL("A0"),
    LEFT_FULL_BACK("A1"),
    FULL_BACK("A2"),
    RIGHT_FULL_BACK("A3"),
    LEFT_HALF_BACK("B1"),
    CENTRE_HALF_BACK("B2"),
    RIGHT_HALF_BACK("B3"),
    LEFT_MIDFIELD("C1"),
    CENTRE_MIDFIELD("C2"),
    RIGHT_MIDFIELD("C3"),
    LEFT_HALF_FORWARD("D1"),
    CENTRE_HALF_FORWARD("D2"),
    RIGHT_HALF_FORWARD("D3"),
    LEFT_FULL_FORWARD("E1"),
    FULL_FORWARD("E2"),
    RIGHT_FULL_FORWARD("E3");

    private final String abbreviation;

    private static final Map<String, PitchPositions> lookup = new HashMap<>();

    static {
        for (PitchPositions pp : PitchPositions.values()) {
            lookup.put(pp.getAbbreviation(), pp);
        }
    }

    PitchPositions(String abbreviation) {  this.abbreviation = abbreviation; }

    public String getAbbreviation() {  return abbreviation; }
    public static PitchPositions get(String abbreviation) { return lookup.get(abbreviation); }


}
