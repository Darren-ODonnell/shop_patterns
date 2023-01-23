package com.jwt.controllers;

import com.jwt.models.StatViewCounts;
import com.jwt.models.StatsView;
import com.jwt.services.StatsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatsView> list(){
        return statsViewService.list();
    }
//
//    @GetMapping(value={"/countByStatNameFixtureDate"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountPlayerDate> countByStatNameFixtureDate(@RequestParam("statname") String statname,
//                                                                             @RequestParam("fixture_date") String fixtureDateStr){
//        return statsViewService.countByStatNameFixtureDate(statname, fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countByStatNameSeason"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountSeason> countByStatnameSeason(@RequestParam("statname") String statname,
//                                                                    @RequestParam("season") int season){
//        return statsViewService.countByStatnameSeason(statname, season);
//    }
//
//    @GetMapping(value={"/countByPlayerFixtureDate"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountPlayerFixtureDate> countByFirstnameLastnameFixtureDate(@RequestParam("firstname") String firstname,
//                                                                                             @RequestParam("lastname") String lastname,
//                                                                                             @RequestParam("fixture_date") String fixtureDateStr){
//        return statsViewService.countByFirstnameLastnameFixtureDate(firstname, lastname,  fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countByPlayerSeason"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountPlayerSeason> countByFirstnameLastnameSeason(@RequestParam("firstname") String firstname,
//                                                                                   @RequestParam("lastname") String lastname,
//                                                                                   @RequestParam("season") int season){
//        return statsViewService.countByFirstnameLastnameSeason(firstname, lastname, season);
//    }
//
//    @GetMapping(value={"/countByFixtureDate"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountFixtureDate> countByFixtureDate(@RequestParam("fixture_date") String fixtureDateStr) {
//        return statsViewService.countByFixtureDate(fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countBySeason"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatCountSeason> countBySeason() {
//        return statsViewService.countBySeason();
//    }
//
//    @GetMapping(value={"/countStatsByFixture"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<Key<Date, BigInteger>> countStatsByFixture(@RequestParam("statname") String statName) {
//        return statsViewService.countStatsByFixture(statName);
//    }
//    @GetMapping(value={"/countStatsBySeason"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<Key<Integer, BigInteger>> countStatsBySeason(@RequestParam("statname") String statName) {
//        return statsViewService.countStatsBySeason(statName);
//    }

    // ----------------------------------------------------------------------------------------------------
    /* Same endpoints as above except using a single class returned rather than one of multiple classes  */
    // ----------------------------------------------------------------------------------------------------
//
//    @GetMapping(value={"/countStatsByFixture2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countStatsByFixture2(@RequestParam("statname") String statName) {
//        return statsViewService.countStatsByFixture2(statName);
//    }
//
//    @GetMapping(value={"/countStatsBySeason2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countStatsBySeason2(@RequestParam("statname") String statName) {
//        return statsViewService.countStatsBySeason2(statName);
//    }
//
//
//    @GetMapping(value={"/countByStatNameFixtureDate2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countByStatNameFixtureDate2(@RequestParam("statname") String statname,
//                                                                          @RequestParam("fixture_date") String fixtureDateStr){
//        return statsViewService.countByStatNameFixtureDate2(statname, fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countByStatNameSeason2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countByStatnameSeason2(@RequestParam("statname") String statname,
//                                                                     @RequestParam("season") int season){
//        return statsViewService.countByStatnameSeason2(statname, season);
//    }
//
//    @GetMapping(value={"/countByPlayerFixtureDate2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countByFirstnameLastnameFixtureDate2(@RequestParam("firstname") String firstname,
//                                                                                   @RequestParam("lastname") String lastname,
//                                                                                   @RequestParam("fixture_date") String fixtureDateStr){
//        return statsViewService.countByFirstnameLastnameFixtureDate2(firstname, lastname,  fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countByPlayerSeason2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countByFirstnameLastnameSeason2(@RequestParam("firstname") String firstname,
//                                                                                   @RequestParam("lastname") String lastname,
//                                                                                   @RequestParam("season") int season){
//        return statsViewService.countByFirstnameLastnameSeason2(firstname, lastname, season);
//    }
//
//    @GetMapping(value={"/countByFixtureDate2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countByFixtureDate2(@RequestParam("fixture_date") String fixtureDateStr) {
//        return statsViewService.countByFixtureDate2(fixtureDateStr);
//    }
//
//    @GetMapping(value={"/countBySeason2"} )
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<StatViewCounts> countBySeason2(@RequestParam("season") int season) {
//        return statsViewService.countBySeason2(season);
//    }
    //--------------------------------------------------------------------------------------------------

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
    public @ResponseBody List<StatViewCounts> countAllStatNameFixtureByPlayer(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        return statsViewService.countAllStatNameFixtureByPlayer(firstname, lastname);
    }

    @GetMapping(value={"/countAllPlayerByFixtureStatName"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerByFixtureStatName(@RequestParam("fixtureDate") String fixtureDate, @RequestParam("statName") String statName) {
        return statsViewService.countAllPlayerByFixtureStatName(fixtureDate, statName);
    }

    @GetMapping(value={"/countAllFixtureByPlayerStatName"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllFixtureByPlayerStatName(@RequestParam("firstname") String firstname, @RequestParam("lastname")String lastname, @RequestParam("statName") String statName) {
        return statsViewService.countAllFixtureByPlayerStatName(firstname, lastname, statName);
    }

    @GetMapping(value={"/countAllStatsByPlayerFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllStatsByPlayerFixtureDate(@RequestParam("firstname") String firstname, @RequestParam("lastname")String lastname, @RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllStatsByPlayerFixtureDate(firstname, lastname, fixtureDate);
    }

    @GetMapping(value={"/countStat"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countStat(@RequestParam("firstname") String firstname, @RequestParam("lastname")String lastname, @RequestParam("fixtureDate") String fixtureDate, @RequestParam("statName") String statName) {
        return statsViewService.countStat(firstname, lastname, fixtureDate, statName);
    }

    @GetMapping(value={"/countAllPlayerStatNameByFixtureDateGroupSuccess"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatViewCounts> countAllPlayerStatNameByFixtureDateGroupSuccess(@RequestParam("fixtureDate") String fixtureDate) {
        return statsViewService.countAllPlayerStatNameByFixtureDateGroupSuccess(fixtureDate);
    }
}
