package com.jwt.controllers;


import com.jwt.models.StatsView;

import com.jwt.models.stats.StatCountPlayerDate;
import com.jwt.models.stats.StatCountSeason;
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
    public @ResponseBody List<StatsView> list(){
        return statsViewService.list();
    }

    @GetMapping(value={"/findByStatNameFixtureDate"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountPlayerDate> findByStatNameFixtureDate(@RequestParam("statname") String statname, @RequestParam("fixture_date") String fixtureDateStr){

        List<StatCountPlayerDate> stats = statsViewService.findByStatNameFixtureDate(statname, fixtureDateStr);
        return stats;
    }

    @GetMapping(value={"/findByStatNameSeason"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<StatCountSeason> findByStatnameSeason(@RequestParam("statname") String statname, @RequestParam("season") int season){
        return statsViewService.findByStatnameSeason(statname, season);
    }
}
