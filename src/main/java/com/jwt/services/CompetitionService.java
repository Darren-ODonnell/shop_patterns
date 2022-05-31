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

    public  Competition findById(Long id){
        Optional<Competition> competition = competitionRepository.findById(id);
        if(competition.isEmpty())
            new MyMessageResponse( "Competition id name does not exist: " + id,MessageTypes.WARN);

        return competition.orElse(new Competition());
    }

    // return Competition by name


    public Competition findByName(CompetitionModel competitionModel) {
        Optional<Competition> competition = competitionRepository.findByName(competitionModel.getName());
        if(competition.isEmpty())
            new MyMessageResponse( "Competition name does not exist : " + competitionModel.getName(),MessageTypes.WARN);

        return competition.orElse(new Competition());
    }

    // add new Competition

    public  ResponseEntity<MessageResponse> add(CompetitionModel competitionModel){

        if(competitionRepository.existsByName(competitionModel.getName()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Competition already exists", MessageTypes.WARN));

        competitionRepository.save(competitionModel.translateModelToCompetition());
        return ResponseEntity.ok(new MyMessageResponse("new Competition added", MessageTypes.INFO));
    }

    // edit/update a competition record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id,  Competition competition){

        // check if exists first
        // insert id
        // then update

        if(!competitionRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        competitionRepository.save(competition);
        return ResponseEntity.ok(new MyMessageResponse("Competition record updated", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> deleteById( Long id){

        if(!competitionRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete competition with id: "+id, MessageTypes.WARN));

        competitionRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Competition deleted with id: " + id, MessageTypes.INFO));
    }
}
