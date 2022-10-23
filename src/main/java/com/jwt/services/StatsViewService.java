package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;

import com.jwt.repositories.StatsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Object> findByStatNameFixtureDate(String statname, String fixtureDate) {
        List<Object> data = statsViewRepository.findByStatNameAndFixtureDate(statname, fixtureDate);
        return data;
    }

    public List<Object> findByStatnameSeason( String statname, int season) {
        List<Object> data = statsViewRepository.findByStatNameAndSeason( statname,  season);
        return data;
    }




    public class Stats {
        Long count;
        String statname;

        public Stats(Long count, String statname) {
            this.count = count;
            this.statname = statname;
        }

        public Long getCount() {
            return count;
        }

        public String getStatname() {
            return statname;
        }
    }

    public class StatCount extends Stats{

        String fixtureDate;

        public StatCount(Long count, String statname, String fixtureDate) {
            super(count, statname);
            this.fixtureDate = fixtureDate;
        }

        public String getFixtureDate() {
            return fixtureDate;
        }
    }

    public class StatCountSeason extends Stats{
        int season;

        public StatCountSeason(Long count, String statname, int season) {
            super(count, statname);
            this.season = season;
        }

        public int getSeason() {
            return season;
        }
    }

    public class StatCountPlayerDate extends StatCount{
        String firstname;
        String lastname;

        public StatCountPlayerDate(Long count, String statname, String firstname, String lastname, String fixtureDate) {
            super(count, statname, fixtureDate);
            this.firstname = firstname;
            this.lastname = lastname;
        }
    }



}
