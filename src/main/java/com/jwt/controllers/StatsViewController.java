package com.jwt.controllers;

import com.jwt.models.Fixture;
import com.jwt.models.StatViewCounts;
import com.jwt.models.StatsView;
import com.jwt.services.StatsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/stats_view"})
public class StatsViewController {

    public final StatsViewService statsViewService;

    @Autowired
    public StatsViewController(StatsViewService statsViewService) {        this.statsViewService = statsViewService;    }

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatsView> list(){
        return statsViewService.list();
    }

    @GetMapping(value={"/countAllPlayerStat"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStat() {
        return statsViewService.countAllPlayerStat();
    }

    @GetMapping(value={"/countAllPlayerFixtureByStatName"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerFixtureByStatName(@RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerFixtureByStatName(statName);
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDate(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDate(fixtureDate);
    }

    @GetMapping(value={"/countAllStatNameFixtureByPlayer"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllStatNameFixtureByPlayer(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname") String lastname) {
        return statsViewService.countAllStatNameFixtureByPlayer(firstname, lastname);
    }

    @GetMapping(value={"/countAllPlayerByFixtureStatName"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerByFixtureStatName(@RequestParam("fixtureDate") String fixtureDate,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerByFixtureStatName(fixtureDate, statName);
    }

    @GetMapping(value={"/countAllFixtureByPlayerStatName"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllFixtureByPlayerStatName(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname")String lastname,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllFixtureByPlayerStatName(firstname, lastname, statName);
    }

    @GetMapping(value={"/countAllStatsByPlayerFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllStatsByPlayerFixtureDate(@RequestParam("firstname") String firstname,
                                                                               @RequestParam("lastname")String lastname,
                                                                               @RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllStatsByPlayerFixtureDate(firstname, lastname, fixtureDate);
    }

    @GetMapping(value={"/countStat"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countStat(@RequestParam("firstname") String firstname,
                                                        @RequestParam("lastname")String lastname,
                                                        @RequestParam("fixtureDate") String fixtureDate,
                                                        @RequestParam("statName") String statName) {
        return statsViewService.countStat(firstname, lastname, fixtureDate, statName);
    }


    @GetMapping(value={"/countAllPlayerStatHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatHeatMap() {
        return statsViewService.countAllPlayerStatHeatMap();
    }

    @GetMapping(value={"/countAllPlayerFixtureByStatNameHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerFixtureByStatNameHeatMap(@RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerFixtureByStatNameHeatMap(statName);
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDateHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDateHeatMap(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDateHeatMap(fixtureDate);
    }

    @GetMapping(value={"/countAllStatNameFixtureByPlayerHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllStatNameFixtureByPlayerHeatMap(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname") String lastname) {
        return statsViewService.countAllStatNameFixtureByPlayerHeatMap(firstname, lastname);
    }

    @GetMapping(value={"/countAllPlayerByFixtureStatNameHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerByFixtureStatNameHeatMap(@RequestParam("fixtureDate") String fixtureDate,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerByFixtureStatNameHeatMap(fixtureDate, statName);
    }

    @GetMapping(value={"/countAllFixtureByPlayerStatNameHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllFixtureByPlayerStatNameHeatMap(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname")String lastname,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllFixtureByPlayerStatNameHeatMap(firstname, lastname, statName);
    }

    @GetMapping(value={"/countAllStatsByPlayerFixtureDateHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllStatsByPlayerFixtureDateHeatMap(@RequestParam("firstname") String firstname,
                                                                               @RequestParam("lastname")String lastname,
                                                                               @RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllStatsByPlayerFixtureDateHeatMap(firstname, lastname, fixtureDate);
    }

    @GetMapping(value={"/countStatHeatMap"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countStatHeatMap(@RequestParam("firstname") String firstname,
                                                        @RequestParam("lastname")String lastname,
                                                        @RequestParam("fixtureDate") String fixtureDate,
                                                        @RequestParam("statName") String statName) {
        return statsViewService.countStatHeatMap(firstname, lastname, fixtureDate, statName);
    }

    @GetMapping(value={"/countStatsPlayerAnalysis"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countStatsPlayerAnalysis(@RequestParam("firstname") String firstname,
                                                               @RequestParam("lastname")String lastname){
        return statsViewService.countStatsPlayerAnalysis(firstname, lastname);
    }

    @GetMapping(value={"/countStatsAllPlayerAnalysis"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countStatsPlayerAnalysis(){
        return statsViewService.countStatsAllPlayerAnalysis();
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDateGroupSuccess"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDateGroupSuccess(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDateGroupSuccess(fixtureDate);
    }

    @GetMapping(value={"/averageScoreByOpposition"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> averageScoreByOpposition(@RequestParam("club_name") String clubName) {
        return statsViewService.averageScoreByOpposition(clubName);
    }

    @GetMapping(value={"/averageByStatNameByOpposition"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> averageByStatNameByOpposition( @RequestParam("club_name") String opposition) {
        return statsViewService.averageByStatNameByOpposition( opposition);
    }


    @GetMapping(value={"/findWinsByOpposition"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fixture> findWinsByOpposition(@RequestParam("club_name") String opposition) {
        return statsViewService.findWinsByOpposition( opposition);
    }




    @GetMapping(value={"/getAvgStatsForWinsByOpponent"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> getAvgStatsForWinsByOpponent(@RequestParam("club_name") String team) {
        return statsViewService.getAvgStatsForWinsByOpponent( team);
    }

    @GetMapping(value={"/getAvgStatsForLossesByOpponent"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> getAvgStatsForLossesByOpponent(@RequestParam("club_name") String team) {
        return statsViewService.getAvgStatsForLossesByOpponent( team);
    }

    @GetMapping(value={"/getStatsForLastFiveFixturesWon"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> getStatsForLastFiveFixturesWon() {
        return statsViewService.getStatsForLastFiveFixturesWon();
    }

    @GetMapping(value={"/getStatsForLastFiveFixturesLost"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<StatViewCounts> getStatsForLastFiveFixturesLost() {
        return statsViewService.getStatsForLastFiveFixturesLost();
    }

}
