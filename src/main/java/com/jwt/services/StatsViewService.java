package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.StatViewCounts;
import com.jwt.models.StatsView;
import com.jwt.models.stats.*;
import com.jwt.repositories.StatsViewRepository;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsViewService {
    final int KEY = 0;
    final int COUNT = 1;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(date);
    }

    public List<StatCountPlayerDate> countByStatNameFixtureDate(String statname, String fixtureDateStr) {
        List<Object[]> data  = statsViewRepository.countByStatNameAndFixtureDate(statname,stringToDate(fixtureDateStr));
        List<StatCountPlayerDate> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerDate stat = new StatCountPlayerDate((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountSeason> countByStatnameSeason( String statname, int season) {
        List<Object[]> data = statsViewRepository.countByStatNameAndSeason( statname,  season);
        List<StatCountSeason> stats = new ArrayList<>();
        for(Object o: data){
            StatCountSeason stat = new StatCountSeason((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountPlayerFixtureDate> countByFirstnameLastnameFixtureDate(String firstname, String lastname, String fixtureDateStr) {
        List<Object[]> data = statsViewRepository.countByFirstnameAndLastnameAndFixtureDate( firstname, lastname, stringToDate(fixtureDateStr));
        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountPlayerFixtureDate> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerFixtureDate stat = new StatCountPlayerFixtureDate((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountPlayerSeason> countByFirstnameLastnameSeason(String firstname, String lastname, int season) {
        List<Object[]> data = statsViewRepository.countByFirstnameAndLastnameAndSeason( firstname, lastname, season);
        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountPlayerSeason> stats = new ArrayList<>();
        for(Object o: data){
            StatCountPlayerSeason stat = new StatCountPlayerSeason((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountFixtureDate> countByFixtureDate(String fixtureDateStr) {
        List<Object[]> data = statsViewRepository.countByFixtureDate( stringToDate(fixtureDateStr));
        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountFixtureDate> stats = new ArrayList<>();
        for(Object o: data){
            StatCountFixtureDate stat = new StatCountFixtureDate((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    public List<StatCountSeason> countBySeason(int season) {
        List<Object[]> data = statsViewRepository.countBySeason( season );
        // convert the List<Object> to specific class to be returned by the controller
        List<StatCountSeason> stats = new ArrayList<>();
        for(Object o: data){
            StatCountSeason stat = new StatCountSeason((Object[]) o);
            stats.add(stat);
        }
        return stats;
    }

    // get count of statname for each fixture
    // returns fixturedate,count
    public List<Key<Date, BigInteger>> countStatsByFixture(String statName) {
        List<Object[]> fixtureDates = statsViewRepository.countDistinctByFixtureDate(statName);
        return mapData(fixtureDates, Date.class);
    }
    public List<Key<Integer, BigInteger>> countStatsBySeason(String statName) {
        List<Object[]> seasons = statsViewRepository.countDistinctBySeason(statName);
        return mapData(seasons, Integer.class);
    }

    // Take an Object[] and map to list<T, BigInteger>  of key value pairs using generics
    // returns T / BigInteger, To get T the class is passed as a parameter.
    // Note the new class Key also uses generics.

    @SuppressWarnings({"unchecked", "rawtypes"})
    private <T> List<Key<T, BigInteger>>  mapData(List<Object[]> seasons, Class T) {
        ArrayList<Key<T, BigInteger>> stats = new ArrayList<>();

        for(Object[] obj : seasons) {
            Key<T, BigInteger> stat = new Key<>();

            stat.setKey((T) obj[KEY]);
            stat.setCount((BigInteger) obj[COUNT]);

            stats.add(stat);
        }
        return stats;
    }
    // ----------------------------------------------------------------------------------------------------
    /* Same endpoints as above except using a single class returned rather than one of multiple classes  */
    // ----------------------------------------------------------------------------------------------------


    public List<StatViewCounts> countStatsByFixture2(String statName) {
        List<Object[]> fixtureDates = statsViewRepository.countDistinctByFixtureDate2(statName);
        return mapData2(fixtureDates);
    }


    public List<StatViewCounts> countStatsBySeason2(String statName) {
        List<Object[]> seasons = statsViewRepository.countDistinctStatName2(statName);
        return mapData2(seasons);
    }

    public List<StatViewCounts> countByStatNameFixtureDate2(String statname, String fixtureDateStr) {
    List<Object[]> data  = statsViewRepository.countByStatNameAndFixtureDate2(statname,stringToDate(fixtureDateStr));
    return mapData2(data);
}

    public List<StatViewCounts> countByStatnameSeason2( String statname, int season) {
        List<Object[]> data = statsViewRepository.countByStatNameAndSeason2( statname,  season);
        return mapData2(data);
    }

    public List<StatViewCounts> countByFirstnameLastnameFixtureDate2(String firstname, String lastname, String fixtureDateStr) {
        List<Object[]> data = statsViewRepository.countByFirstnameAndLastnameAndFixtureDate2( firstname, lastname, stringToDate(fixtureDateStr));
        return mapData2(data);
    }

    public List<StatViewCounts> countByFirstnameLastnameSeason2(String firstname, String lastname, int season) {
        List<Object[]> data = statsViewRepository.countByFirstnameAndLastnameAndSeason2( firstname, lastname, season);
        return mapData2(data);
    }

    public List<StatViewCounts> countByFixtureDate2(String fixtureDateStr) {
        List<Object[]> data = statsViewRepository.countByFixtureDate2( stringToDate(fixtureDateStr));
        return mapData2(data);
    }

    public List<StatViewCounts> countBySeason2(int season) {
        List<Object[]> data = statsViewRepository.countBySeason2( season );
        return mapData2(data);
    }

    private List<StatViewCounts> mapData2(List<Object[]> seasons) {
        List<StatViewCounts> counts = new ArrayList<>();
        for(Object[] record : seasons) {
            StatViewCounts stat = new StatViewCounts(record);
            counts.add(stat);
        }
        return counts;
    }
}
