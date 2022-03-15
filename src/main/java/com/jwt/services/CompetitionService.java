package com.jwt.services;

import com.jwt.models.Competition;
import com.jwt.models.CompetitionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.CompetitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);
    CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }


    // return all Competitions - done


    public List<Competition> list(){
        return competitionRepository.findAll();
    }

    // delete by id

    public ResponseEntity<MessageResponse> deleteById( Long id){

        logger.info("deleteCompetition by id = "+id);
        if(competitionRepository.existsById(id))
            competitionRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete competition with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Competition deleted with id: " + id));
    }

    // return Competition by id


    public  Optional<Competition> findById(Long id){
        return competitionRepository.findById(id);
    }

    // return Competition by name


    public Competition findByName(CompetitionModel competitionModel) {
        if(!competitionRepository.existsByName(competitionModel.getName())) {
            logger.info("Competition name does not exist : " + competitionModel.getName());
            return null;
        } else {
            return competitionRepository.findByName(competitionModel.getName());
        }
    }

    // add new Competition


    public  ResponseEntity<MessageResponse> add(CompetitionModel competitionModel){

        if(competitionRepository.existsByName(competitionModel.getName()))
            return ResponseEntity.ok(new MessageResponse("Error: Competition already exists"));
        else
            competitionRepository.save(competitionModel.translateModelToCompetition());

        return ResponseEntity.ok(new MessageResponse("new Competition added"));
    }

    // edit/update a competition record - only if record with id exists


    public ResponseEntity<MessageResponse> update(Long id,  CompetitionModel competitionModel){

        // check if exists first
        // insert id
        // then update

        if(competitionRepository.existsById(id)) {
            Competition competition = competitionModel.translateModelToCompetition();
            competition.setId(id);
            competitionRepository.save(competition);
        }
        else
            return ResponseEntity.ok(new MessageResponse("Error: Id does not exist ["+id+"] -> cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Competition record updated"));
    }
}
