package com.jwt.controllers;


import com.jwt.models.StatsView;

import com.jwt.models.stats.*;
import com.jwt.services.StatsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
}
