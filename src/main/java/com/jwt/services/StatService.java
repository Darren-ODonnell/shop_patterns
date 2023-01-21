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

import java.util.List;
import java.util.Optional;


@Service
public class StatService {

    StatRepository statRepository;
    PitchGridRepository pitchGridRepository;
    PlayerRepository playerRepository;
    StatNameRepository statNameRepository;
    FixtureRepository fixtureRepository;

    @Autowired
    public StatService(StatRepository statRepository, PitchGridRepository pitchGridRepository, PlayerRepository playerRepository, StatNameRepository statNameRepository, FixtureRepository fixtureRepository) {
        this.statRepository = statRepository;
        this.pitchGridRepository = pitchGridRepository;
        this.playerRepository = playerRepository;
        this.statNameRepository = statNameRepository;
        this.fixtureRepository = fixtureRepository;

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
