package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;
import com.jwt.models.stats.*;
import com.jwt.repositories.StatNameRepository;
import com.jwt.repositories.StatsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsViewService {
    @Value("${club.name}")
    String clubName;

    final int KEY = 0;
    final int COUNT = 1;

    StatsViewRepository statsViewRepository;
    StatNameService statNameService;
    StatService statService;
    ClubService clubService;
    FixtureService fixtureService;



    @Autowired
    public StatsViewService(StatsViewRepository statsViewRepository, ClubService clubService, StatNameService statNameService
                          ) {
        this.statsViewRepository = statsViewRepository;
        this.clubService = clubService;
        this.statNameService = statNameService;

    }

    @Autowired
    public void setFixtureService(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }
    @Autowired
    public void setStatService(StatService statService) {
        this.statService = statService;
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
    public List<StatViewCounts> averageByStatNameByOpposition( String opposition) {
        List<Object[]> data = statsViewRepository.averageByStatNameByOpposition( clubName, opposition);
        return mapData2(data);
    }


    public List<Fixture> findWinsByOpposition(String team) {
        //Get all fixtures where judes are playing against opposition where home/ opposition is judes AND home/opposition is team
        Long clubId = clubService.findByName(team).getId();
        Long id = clubService.findByName(clubName).getId();

        List<Fixture> fixturesVsOpponent = fixtureService.findByOppositionId(clubId);

        List<Fixture> fixturesWon = new ArrayList<>();

        for(Fixture fixture : fixturesVsOpponent) {
            Result result = statService.scoreByFixtureDate(fixture.getFixtureDate());
            long homeScore = result.getHomeScorePoints();
            long awayScore = result.getAwayScorePoints();
            if (Objects.equals(clubId, fixture.getHomeTeam().getId()) && homeScore < awayScore)
                fixturesWon.add(fixture);
            if(Objects.equals(clubId, fixture.getAwayTeam().getId()) && awayScore > homeScore)
                fixturesWon.add(fixture);
        }
        return fixturesWon;
    }

    public List<StatViewCounts> getAvgStatsForWinsByOpponent(String team){
        List<Fixture> fixturesWon = findWinsByOpposition(team);

        List<List<Stat>> statsForEachWin = new ArrayList<>();
        for(Fixture fixture: fixturesWon){
            List<Stat> statsForWin = statService.findByFixtureId(fixture.getId());
            statsForEachWin.add(statsForWin);
        }

        HashMap<String, Integer> statCounts = new HashMap<>();
        for(List<Stat> statsPerWin: statsForEachWin){
            for(int i = 0; i < statsPerWin.size(); i++){
                String stat = statsPerWin.get(i).getStatName().getName();
                if (statCounts.containsKey(stat)) {
                    int count = statCounts.get(stat);
                    statCounts.put(stat, count + 1);
                } else {
                    statCounts.put(stat, 1);
                }
            }
        }
        List<StatViewCounts> statViewCounts = new ArrayList<>();
        for(String key: statCounts.keySet()){
            StatViewCounts statViewCount = new StatViewCounts(key, BigInteger.valueOf(statCounts.get(key)));
            statViewCounts.add(statViewCount);
        }

        Collections.sort(statViewCounts, Comparator.comparing(StatViewCounts::getCount).reversed());
        return statViewCounts;
    }
}
