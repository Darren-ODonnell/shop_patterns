package com.jwt.controllers;

import com.jwt.models.StatViewCounts;
import com.jwt.models.StatsView;
import com.jwt.models.stats.*;
import com.jwt.services.Key;
import com.jwt.services.StatsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/stats_view"})
public class StatsViewController {

    public final StatsViewService statsViewService;

    @Autowired
    public StatsViewController(StatsViewService statsViewService) {        this.statsViewService = statsViewService;    }

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatsView> list(){
        return statsViewService.list();
    }

    @GetMapping(value={"/findByStatNameFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountPlayerDate> findByStatNameFixtureDate(@RequestParam("statname") String statname,
                                                                             @RequestParam("fixture_date") String fixtureDateStr){
        return statsViewService.findByStatNameFixtureDate(statname, fixtureDateStr);
    }

    @GetMapping(value={"/findByStatNameSeason"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountSeason> findByStatnameSeason(@RequestParam("statname") String statname,
                                                                    @RequestParam("season") int season){
        return statsViewService.findByStatnameSeason(statname, season);
    }

    @GetMapping(value={"/findByPlayerFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountPlayerFixtureDate> findByFirstnameLastnameFixtureDate(@RequestParam("firstname") String firstname,
                                                                                             @RequestParam("lastname") String lastname,
                                                                                             @RequestParam("fixture_date") String fixtureDateStr){
        return statsViewService.findByFirstnameLastnameFixtureDate(firstname, lastname,  fixtureDateStr);
    }

    @GetMapping(value={"/findByPlayerSeason"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountPlayerSeason> findByFirstnameLastnameSeason(@RequestParam("firstname") String firstname,
                                                                                   @RequestParam("lastname") String lastname,
                                                                                   @RequestParam("season") int season){
        return statsViewService.findByFirstnameLastnameSeason(firstname, lastname, season);
    }

    @GetMapping(value={"/findByFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountFixtureDate> findByFixtureDate(@RequestParam("fixture_date") String fixtureDateStr) {
        return statsViewService.findByFixtureDate(fixtureDateStr);
    }

    @GetMapping(value={"/findBySeason"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountSeason> findBySeason(@RequestParam("season") int season) {
        return statsViewService.findBySeason(season);
    }

    @GetMapping(value={"/chartStatsByFixture"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Key<Date, BigInteger>> chartStatsByFixture(@RequestParam("statname") String statName) {
        return statsViewService.chartStatsByFixture(statName);
    }

    // ----------------------------------------------------------------------------------------------------
    /* Same endpoints as above except using a single class returned rather than one of multiple classes  */
    // ----------------------------------------------------------------------------------------------------


    @GetMapping(value={"/chartStatsByFixture2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> chartStatsByFixture2(@RequestParam("statname") String statName) {
        return statsViewService.chartStatsByFixture2(statName);
    }

    @GetMapping(value={"/chartStatsBySeason2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> chartStatsBySeason2(@RequestParam("statname") String statName) {
        return statsViewService.chartStatsBySeason2(statName);
    }


    @GetMapping(value={"/findByStatNameFixtureDate2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findByStatNameFixtureDate2(@RequestParam("statname") String statname,
                                                                             @RequestParam("fixture_date") String fixtureDateStr){
        return statsViewService.findByStatNameFixtureDate2(statname, fixtureDateStr);
    }

    @GetMapping(value={"/findByStatNameSeason2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findByStatnameSeason2(@RequestParam("statname") String statname,
                                                                    @RequestParam("season") int season){
        return statsViewService.findByStatnameSeason2(statname, season);
    }

    @GetMapping(value={"/findByPlayerFixtureDate2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findByFirstnameLastnameFixtureDate2(@RequestParam("firstname") String firstname,
                                                                                             @RequestParam("lastname") String lastname,
                                                                                             @RequestParam("fixture_date") String fixtureDateStr){
        return statsViewService.findByFirstnameLastnameFixtureDate2(firstname, lastname,  fixtureDateStr);
    }

    @GetMapping(value={"/findByPlayerSeason2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findByFirstnameLastnameSeason2(@RequestParam("firstname") String firstname,
                                                                                   @RequestParam("lastname") String lastname,
                                                                                   @RequestParam("season") int season){
        return statsViewService.findByFirstnameLastnameSeason2(firstname, lastname, season);
    }

    @GetMapping(value={"/findByFixtureDate2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findByFixtureDate2(@RequestParam("fixture_date") String fixtureDateStr) {
        return statsViewService.findByFixtureDate2(fixtureDateStr);
    }

    @GetMapping(value={"/findBySeason2"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> findBySeason2(@RequestParam("season") int season) {
        return statsViewService.findBySeason2(season);
    }
}
