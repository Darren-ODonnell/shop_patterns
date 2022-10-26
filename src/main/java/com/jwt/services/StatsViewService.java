package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;

import com.jwt.models.stats.*;
import com.jwt.repositories.StatsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class StatsViewService {

    StatsViewRepository statsViewRepository;

    @Autowired
    public StatsViewService(StatsViewRepository statsViewRepository) {
        this.statsViewRepository = statsViewRepository;
    }

    // return all Stats
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<StatsView> list(){
        List<StatsView> statsView = statsViewRepository.findAll();
        if(statsView.isEmpty()) new MyMessageResponse("Error: No Stats listed", MessageTypes.WARN);
        return statsView;
    }

    public Date stringToDate(String date) {
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StatCountPlayerDate> findByStatNameFixtureDate(String statname, String fixtureDateStr) {

        List<Object> data  = statsViewRepository.findByStatNameAndFixtureDate(statname,stringToDate(fixtureDateStr));
        // firstname, lastname, statnate, fixturedate, count
        // StatCountFixtureDate

        List<StatCountPlayerDate> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerDate stat = new StatCountPlayerDate((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }


    public List<StatCountSeason> findByStatnameSeason( String statname, int season) {
        List<Object> data = statsViewRepository.findByStatNameAndSeason( statname,  season);

        List<StatCountSeason> stats = new ArrayList<>();
        for(Object o: data){
            StatCountSeason stat = new StatCountSeason((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountPlayerFixtureDate> findByFirstnameLastnameFixtureDate(String firstname, String lastname, String fixtureDateStr) {
        // collect data
        List<Object> data = statsViewRepository.findByFirstnameAndLastnameAndFixtureDate( firstname, lastname, stringToDate(fixtureDateStr));


        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountPlayerFixtureDate> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerFixtureDate stat = new StatCountPlayerFixtureDate((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountPlayerSeason> findByFirstnameLastnameSeason(String firstname, String lastname, int season) {
        // collect data
        List<Object> data = statsViewRepository.findByFirstnameAndLastnameAndSeason( firstname, lastname, season);


        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountPlayerSeason> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerSeason stat = new StatCountPlayerSeason((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }


}
