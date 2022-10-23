package com.jwt.controllers;


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

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<com.jwt.models.StatsView> list(){
        return statsViewService.list();
    }

    @GetMapping(value={"/findByStatNameFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Object> findByStatNameFixtureDate(@RequestParam("statname") String statname, @RequestParam("fixture_date") String fixtureDate){
        return statsViewService.findByStatNameFixtureDate(statname, fixtureDate);
    }

    @GetMapping(value={"/findByStatnameSeason"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Object> findByStatnameSeason(@RequestParam("statname") String statname, @RequestParam("season") int season){
        return statsViewService.findByStatnameSeason(statname, season);
    }



}
