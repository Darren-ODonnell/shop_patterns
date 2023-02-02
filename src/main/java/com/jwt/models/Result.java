package com.jwt.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Result {
    String homeTeam ;
    String awayTeam ;
    // individual breakdown of home/away goals/points
    long homePoints ;
    long homeGoals ;
    long awayPoints ;
    long awayGoals ;

    // all scores converted to points - so winner can be easily determined
    long awayScorePoints ;
    long homeScorePoints ;

    // display result as string for easy display.
    String homeScoreString ;
    String awayScoreString ;

    // as above but with points tally shown
    String homeScoreStringWithPointsTotal ;
    String awayScoreStringWithPointsTotal ;

    public String getHomeScore() {
        return String.format("%d - %d", homeGoals, homePoints);
    }
    public String getAwayScore() {
        return String.format("%d - %d", awayGoals, awayPoints);
    }
    public String getHomeScoreWithPointsTotal() {
        return String.format("%d - %d (%d)", homeGoals, homePoints, homeGoals * 3 + homePoints);
    }
    public String getAwayScoreWithPointsTotal() {
        return String.format("%d - %d (%d)", awayGoals, awayPoints, awayGoals * 3 + awayPoints);
    }
}
