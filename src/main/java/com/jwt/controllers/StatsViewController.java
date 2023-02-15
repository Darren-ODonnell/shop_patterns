package com.jwt.controllers;

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
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatsView> list(){
        return statsViewService.list();
    }

    @GetMapping(value={"/countAllPlayerStat"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStat() {
        return statsViewService.countAllPlayerStat();
    }

    @GetMapping(value={"/countAllPlayerFixtureByStatName"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerFixtureByStatName(@RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerFixtureByStatName(statName);
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDate(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDate(fixtureDate);
    }

    @GetMapping(value={"/countAllStatNameFixtureByPlayer"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllStatNameFixtureByPlayer(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname") String lastname) {
        return statsViewService.countAllStatNameFixtureByPlayer(firstname, lastname);
    }

    @GetMapping(value={"/countAllPlayerByFixtureStatName"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerByFixtureStatName(@RequestParam("fixtureDate") String fixtureDate,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerByFixtureStatName(fixtureDate, statName);
    }

    @GetMapping(value={"/countAllFixtureByPlayerStatName"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllFixtureByPlayerStatName(@RequestParam("firstname") String firstname,
                                                                              @RequestParam("lastname")String lastname,
                                                                              @RequestParam("statName") String statName) {
        return statsViewService.countAllFixtureByPlayerStatName(firstname, lastname, statName);
    }

    @GetMapping(value={"/countAllStatsByPlayerFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllStatsByPlayerFixtureDate(@RequestParam("firstname") String firstname,
                                                                               @RequestParam("lastname")String lastname,
                                                                               @RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllStatsByPlayerFixtureDate(firstname, lastname, fixtureDate);
    }

    @GetMapping(value={"/countStat"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countStat(@RequestParam("firstname") String firstname,
                                                        @RequestParam("lastname")String lastname,
                                                        @RequestParam("fixtureDate") String fixtureDate,
                                                        @RequestParam("statName") String statName) {
        return statsViewService.countStat(firstname, lastname, fixtureDate, statName);
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDateGroupSuccess"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDateGroupSuccess(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDateGroupSuccess(fixtureDate);
    }

    @GetMapping(value={"/averageScoreByOpposition"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> averageScoreByOpposition(@RequestParam("club_name") String clubName) {
        return statsViewService.averageScoreByOpposition(clubName);
    }


}
