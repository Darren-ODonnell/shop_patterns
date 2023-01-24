package com.jwt.models;

import lombok.Builder;

@Builder
public class Result {
    String homeTeam = "";
    String awayTeam = "";
    // individual breakdown of home/away goals/points
    long homePoints = 0;
    long homeGoals = 0;
    long awayPoints = 0;
    long awayGoals = 0;

    // all scores converted to points - so winnert can be easily determined
    long awayScorePoints = 0;
    long homeScorePoints = 0;

    // display result as string for easy display.
    String homeScoreString = "0 - 0";
    String awayScoreString = "0 - 0";

    // as above but with points tally shown
    String homeScoreStringWithPointsTotal = "0 - 0 (0)";
    String awayScoreStringWithPointsTotal = "0 - 0 (0)";
}
