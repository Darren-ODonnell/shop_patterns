package com.jwt.enums;

import java.util.HashMap;
import java.util.Map;

// player grades - agreed with county board

public enum Grade {
    SENIOR_1("S1"),
    SENIOR_2("S2"),
    INTER_1("I1"),
    INTER_2("I2"),
    JUNIOR_1("J1"),
    JUNIOR_2("J2"),
    JUNIOR_3("J3"),
    JUNIOR_4("J4"),
    JUNIOR_5("J5"),
    JUNIOR_6("J6"),
    JUNIOR_7("J7"),
    JUNIOR_8("J8"),
    JUNIOR_9("J9"),
    JUNIOR_10("J10");

    private final String abbreviation;

    private static final Map<String, Grade> lookup = new HashMap<>();

    static {
        for (Grade g : Grade.values()) {
            lookup.put(g.getAbbreviation(), g);
        }
    }

    Grade(String abbreviation) {  this.abbreviation = abbreviation; }

    public String getAbbreviation() {  return abbreviation; }
    public static Grade get(String abbreviation) { return lookup.get(abbreviation); }
}
