package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;

import com.jwt.models.stats.StatCountPlayerDate;
import com.jwt.models.stats.StatCountSeason;
import com.jwt.repositories.StatsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // attempt at generics

//    public <T> List<T> convert(Object obj, List<Object> data) {
//        List<T> stats = new ArrayList<>();
//        for(Object o: data){
//            T stat = new T((Object[]) o);
//            stats.add(stat);
//        }
//        return stats;
//    }
}
