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


    private List<StatViewCounts> mapData2(List<Object[]> seasons) {
        List<StatViewCounts> counts = new ArrayList<>();
        for(Object[] record : seasons) {
            StatViewCounts stat = new StatViewCounts(record);
            counts.add(stat);
        }
        return counts;
    }

    // --------------------------------------------------------------------------------------------------------

    public List<StatViewCounts> countAllPlayerStat() {
        List<Object[]> data = statsViewRepository.countAllPlayerStat();
        return mapData2(data);
    }

    public List<StatViewCounts> countAllPlayerFixtureByStatName(String statName) {
        List<Object[]> data = statsViewRepository.countAllPlayerFixtureByStatName(statName);
        return mapData2(data);
    }

    public List<StatViewCounts> countAllPlayerStatNameByFixtureDate(String fixtureDate) {
        List<Object[]> data = statsViewRepository.countAllPlayerStatNameByFixtureDate(fixtureDate);
        return mapData2(data);
    }
    public List<StatViewCounts> countAllStatNameFixtureByPlayer(String firstname, String lastname) {
        List<Object[]> data = statsViewRepository.countAllStatNameFixtureByPlayer(firstname, lastname);
        return mapData2(data);
    }

    public List<StatViewCounts> countAllPlayerByFixtureStatName(String fixtureDate, String statName) {
        List<Object[]> data = statsViewRepository.countAllPlayerByFixtureStatName(fixtureDate, statName);
        return mapData2(data);
    }

    public List<StatViewCounts> countAllFixtureByPlayerStatName(String firstname, String lastname, String statName) {
        List<Object[]> data = statsViewRepository.countAllFixtureByPlayerStatName(firstname, lastname, statName);
        return mapData2(data);
    }

    public List<StatViewCounts> countAllStatsByPlayerFixtureDate(String firstname, String lastname, String fixtureDate) {
        List<Object[]> data = statsViewRepository.countAllStatsByPlayerFixtureDate(firstname, lastname, fixtureDate);
        return mapData2(data);
    }

    public List<StatViewCounts> countStat(String firstname, String lastname, String fixtureDate, String statName) {
        List<Object[]> data = statsViewRepository.countStat(firstname, lastname, fixtureDate, statName);
        return mapData2(data);
    }

    public List<StatViewCounts> countAllPlayerStatNameByFixtureDateGroupSuccess(String fixtureDate) {
        List<Object[]> data = statsViewRepository.countAllPlayerStatNameByFixtureDateGroupSuccess(fixtureDate);
        return mapData2(data);
    }

    public List<StatViewCounts> averageScoreByOpposition(String clubName) {
        List<Object[]> data = statsViewRepository.averageScoreByOpposition(clubName);

        return mapData2(data);
    }

}
