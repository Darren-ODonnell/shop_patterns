package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Competition;
import com.jwt.models.CompetitionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    // return all Competitions - done

    public List<Competition> list(){
        return competitionRepository.findAll();
    }

    // return Competition by id

    public Competition findById(Long id){
        Optional<Competition> competition = competitionRepository.findById(id);
        if(competition.isEmpty())
            new MyMessageResponse( "Competition id name does not exist: " + id,MessageTypes.WARN);

        return competition.orElse(new Competition());
    }

    // return Competition by Season

    public List<Competition> findBySeason(CompetitionModel competitionModel) {
        Optional<List<Competition>> competitions = competitionRepository.findBySeason(competitionModel.getSeason());
        if(competitions.isEmpty())
            new MyMessageResponse( "Competition Season does not exist : " + competitionModel.getName(),MessageTypes.WARN);

        return competitions.orElse(new ArrayList<>());

    }

    // return Competition by Name

    public Competition findByName(CompetitionModel competitionModel) {
        Optional<Competition> competition = competitionRepository.findByName(competitionModel.getName());
        if(competition.isEmpty())
            new MyMessageResponse( "Competition name does not exist : " + competitionModel.getName(),MessageTypes.WARN);

        return competition.orElse(new Competition());
    }

    // add new Competition

    public  ResponseEntity<MessageResponse> add(CompetitionModel competitionModel){

        if(!competitionRepository.existsByName(competitionModel.getName())) {
            competitionRepository.save(competitionModel.translateModelToCompetition());
            return ResponseEntity.ok(new MyMessageResponse("new Competition added", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Competition already exists", MessageTypes.WARN));
        }
    }

    // edit/update a competition record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id,  Competition competition){

        // check if exists first
        // insert id
        // then update

        if(competitionRepository.existsById(id)) {
            competitionRepository.save(competition);
            return ResponseEntity.ok(new MyMessageResponse("Competition record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist [" + id + "] -> cannot update record", MessageTypes.WARN));
        }

    }

    // delete by id

    public ResponseEntity<MessageResponse> delete(Competition competition){
        Long id = competition.getId();
        if(competitionRepository.existsById(id)) {
            competitionRepository.deleteById(id);
            return ResponseEntity.ok(new MyMessageResponse("Competition deleted with id: " + id, MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete competition with id: " + id, MessageTypes.WARN));
        }

    }


}
