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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Object> findByStatnameSeason( String statname, int season) {
        List<Object> data = statsViewRepository.findByStatNameAndSeason( statname,  season);
        // firstname, lastname, statnate, season, count

        return data;
    }

    public class Stats {
        BigInteger count;
        String statname;

        public Stats(BigInteger count, String statname) {
            this.count = count;
            this.statname = statname;
        }

        public BigInteger getCount() {
            return count;
        }

        public String getStatname() {
            return statname;
        }
    }

    public class StatCountFixtureDate extends Stats{

        Date fixtureDate;

        public StatCountFixtureDate(BigInteger count, String statname, Date fixtureDate) {
            super(count, statname);
            this.fixtureDate = fixtureDate;
        }

        public StatCountFixtureDate(BigInteger count, String statname) {
            super(count, statname);
        }

        public Date getFixtureDate() {
            return fixtureDate;
        }
    }

    public class StatCountSeason extends Stats{
        int season;

        public StatCountSeason(BigInteger count, String statname, int season) {
            super(count, statname);
            this.season = season;
        }

        public int getSeason() {
            return season;
        }
    }

    public class StatCountPlayerDate extends StatCountFixtureDate{
        String firstname;
        String lastname;

        public StatCountPlayerDate(BigInteger count, String statname, String firstname, String lastname, Date fixtureDate) {
            super(count, statname, fixtureDate);
            this.firstname = firstname;
            this.lastname = lastname;
        }


        public StatCountPlayerDate() {
            super(BigInteger.valueOf(0),"",stringToDate("2022-03-06"));
        }

        public StatCountPlayerDate(Object[] obj ) {
            super((BigInteger) obj[4], (String) obj[2], (Date) obj[3]);
            firstname = (String) obj[0];
            lastname = (String) obj[1];
        }


    }



}
