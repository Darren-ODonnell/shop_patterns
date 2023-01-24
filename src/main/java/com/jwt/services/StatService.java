package com.jwt.services;

// identify the basic crud services which should be included here

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StatService {

    StatRepository statRepository;
    PitchGridRepository pitchGridRepository;
    PlayerRepository playerRepository;
    StatNameRepository statNameRepository;
    FixtureRepository fixtureRepository;
    ClubRepository clubRepository;

    @Autowired
    public StatService(StatRepository statRepository, PitchGridRepository pitchGridRepository, PlayerRepository playerRepository,
                       StatNameRepository statNameRepository, FixtureRepository fixtureRepository, ClubRepository clubRepository) {
        this.statRepository = statRepository;
        this.pitchGridRepository = pitchGridRepository;
        this.playerRepository = playerRepository;
        this.statNameRepository = statNameRepository;
        this.fixtureRepository = fixtureRepository;
        this.clubRepository = clubRepository;
    }

    // return all Stats
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Stat> list(){
        List<Stat> stats = statRepository.findAll();
        if(stats.isEmpty()) new MyMessageResponse("Error: No Stats listed", MessageTypes.WARN);
        return stats;
    }

    // return Stat by id


    public Stat findById( StatId id){
        Optional<Stat> stat = statRepository.findById(id);
        if(stat.isEmpty())
            new MyMessageResponse(String.format("Stat id: %d %s not found", id.getFixtureId(), id.getTimeOccurred()), MessageTypes.ERROR);
        return stat.orElse(new Stat());
    }

    public List<Stat> findByFixtureId(Long fixtureId) {
        Optional<List<Stat>> stats = statRepository.findByFixtureId(fixtureId);
        if(stats.isEmpty())
            new MyMessageResponse(String.format("Stats: No stats found for this fixture Id: %d", fixtureId), MessageTypes.ERROR);
        return stats.orElse(new ArrayList<>());
    }

    // return Stat by id

    public Result scoreByFixtureDate( Date fixtureDate){
        String clubName = "Naomh Jude";
        String FREESCORE = "FS";
        String POINT = "SCPT";
        String GOAL = "SCG";
        boolean SUCCESS = true;

       StatName freeScore = statNameRepository.findById("FS").get();
       StatName point = statNameRepository.findById("SCPT").get();
       StatName goal = statNameRepository.findById("SCG").get();

        long oppositionID = playerRepository.findByLastname("Opposition").get().get(0).getId();

        long ownGoals = 0;
        long ownPoints = 0;
        long oppGoals = 0;
        long oppPoints = 0;

        // get club id
        // get fixture ID - Judes must have played either a home or away fixture

        Long clubId = clubRepository.findByName(clubName).get().getId();
        List<Fixture> fixtures = fixtureRepository.findByFixtureDateAndHomeTeamIdOrFixtureDateAndAwayTeamId(fixtureDate, clubId, fixtureDate, clubId);

        Fixture fixture = fixtures.get(0);

        if (fixture.getId() == null) {
            new MyMessageResponse(String.format("Score: No Fixture with %s found for this date ", clubName, fixtureDate.toString()), MessageTypes.ERROR);
        }


        Optional<List<Stat>> stats = statRepository.findByFixtureId(fixture.getId());


        Optional<List<Stat>> goals = statRepository.findByFixtureIdAndSuccessAndStatNameOrFixtureIdAndSuccessAndStatName(fixture.getId(), SUCCESS, freeScore, fixture.getId(), SUCCESS, goal);
        Optional<List<Stat>> points = statRepository.findByFixtureIdAndSuccessAndStatNameOrFixtureIdAndSuccessAndStatName(fixture.getId(), SUCCESS, freeScore, fixture.getId(), SUCCESS, point);
//        Optional<List<Stat>> points = statRepository.findByFixtureIdAndSuccessAndStatNameOrStatName(fixture.getId(), SUCCESS, FreeScore, Point);


        if(goals.isPresent()) {
            oppGoals = goals.get()
                    .stream()
                    .filter(g -> g.getPlayer().getId() == oppositionID) // 0 = opposition
                    .count();
            ownGoals = goals.get()
                    .stream()
                    .filter(g -> g.getPlayer().getId() != oppositionID)
                    .count();
        }
        if(points.isPresent()) {
            oppPoints = points.get()
                    .stream()
                    .filter(p -> p.getPlayer().getId() == oppositionID) // 0 = opposition
                    .count();
            ownPoints = points.get()
                    .stream()
                    .filter(p -> p.getPlayer().getId() != oppositionID)
                    .count();
        }
        // determine who was playing home/away

        long homeGoals = 0;
        long homePoints = 0;
        long awayGoals = 0;
        long awayPoints = 0;


        if(clubId == fixture.getHomeTeam().getId()) {
            homeGoals = ownGoals;
            homePoints = ownPoints;
            awayGoals = oppGoals;
            awayPoints = oppPoints;
        } else {
            homeGoals = oppGoals;
            homePoints = oppPoints;
            awayGoals = ownGoals;
            awayPoints = ownPoints;
        }

        Result result = Result.builder()
                .homeTeam(fixture.getHomeTeam().getName())
                .awayTeam(fixture.getAwayTeam().getName())
                .homeGoals(homeGoals)
                .homePoints(homePoints)
                .awayGoals(awayGoals)
                .awayPoints(awayPoints)
                .homeScorePoints(homeGoals * 3 + homePoints)
                .awayScorePoints(awayGoals * 3 + awayPoints)
                .homeScoreString(String.format("%d - %d",homeGoals, homePoints))
                .awayScoreString(String.format("%d - %d",awayGoals, awayPoints))
                .homeScoreStringWithPointsTotal(String.format("%d - %d (%d)",homeGoals, homePoints, homeGoals * 3 + homePoints))
                .awayScoreStringWithPointsTotal(String.format("%d - %d (%d)",awayGoals, awayPoints, awayGoals * 3 + awayPoints))
                .build();

        return result;
    }



    // return Stat by name

//    public Stat findByName( StatModel statModel) {
//        Optional<Stat> stat = statRepository.findByStatName(statModel.getStatName());
//        if(stat.isEmpty()) new MyMessageResponse(String.format("Stat name: %s not found", statModel.getStatName()), MessageTypes.INFO);
//        return stat.orElse(new Stat());
//    }

    // add new Stat

    public ResponseEntity<MessageResponse> add(StatModel statModel){

        Optional<Player> player = playerRepository.findById(statModel.getPlayerId());
        Long id = statModel.getFixtureId();
        Optional<Fixture> fixture = fixtureRepository.findById(id);
        Optional<StatName> statName = statNameRepository.findById(statModel.getStatNameId());
        Optional<PitchGrid> location = pitchGridRepository.findById(statModel.getLocationId());

//        StatId statId = new StatId();
//        statId.setFixtureId(fixture.get().getId());
//        statId.setTimeOccurred(statModel.getTimeOccurred());


        StatId statId = new StatId();
        statId.setFixtureId(statModel.getFixtureId());
        statId.setTimeOccurred(statModel.getTimeOccurred());

        if(statRepository.existsById(statId))
            return ResponseEntity.ok(new MyMessageResponse("Error: Stat already exists", MessageTypes.WARN));

        Stat stat = statModel.translateModelToStat( fixtureRepository,  playerRepository, pitchGridRepository,  statNameRepository);
        stat.setId(statId);
        statRepository.save(stat);
        return ResponseEntity.ok(new MyMessageResponse("new Stat added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> delete( Stat stat){
        StatId id = stat.getId();
        if(!statRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Stat with id: "+id, MessageTypes.WARN));

        statRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Stat deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a Stat record - only if record with id exists

    public ResponseEntity<MessageResponse> update(StatId id, Stat stat){

        // check if exists first
        // then update

        if(!statRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        statRepository.save(stat);
        return ResponseEntity.ok(new MyMessageResponse("Stat record updated", MessageTypes.INFO));
    }



}
