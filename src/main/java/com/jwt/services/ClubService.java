package com.jwt.services;

import com.jwt.exceptions.NotFoundException;
import com.jwt.models.Club;
import com.jwt.models.ClubModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.ClubRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    private static final Logger logger = LoggerFactory.getLogger(ClubService.class);
    ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
    // return all Clubs


    public List<Club> list(){
        List<Club> clubs = clubRepository.findAll();
        if(clubs == null)
            throw new NotFoundException("No Clubs listed");

        return clubs;
    }

    // delete by id


    public ResponseEntity<MessageResponse> deleteById( Long id){

        logger.info("delete Club by id = "+id);
        if(clubRepository.existsById(id))
            clubRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete Club with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Club deleted with id: " + id));
    }

    // return Club by id

    public Club findById( Long id){

        return clubRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Club id: %d not found", id)));
    }

    // return Club by name

    public Club findByName( String name) {
        Club club = clubRepository.findByName(name);
        if(club == null)
            throw new NotFoundException(String.format("Club name: %s not found", name));

        return club;
    }

    // add new Club

    public ResponseEntity<MessageResponse> add(ClubModel clubModel){

        if(clubRepository.existsByName(clubModel.getName()))
            return ResponseEntity.ok(new MessageResponse("Error: Club already exists"));
        else
            clubRepository.save(clubModel.translateModelToClub());

        return ResponseEntity.ok(new MessageResponse("new Club added"));
    }

    // edit/update a Club record - only if record with id exists


    public ResponseEntity<MessageResponse> update(Long id, ClubModel clubModel){

        // check if exists first
        // then update

        if(clubRepository.existsById(id)) {
            Club club = clubModel.translateModelToClub();
            club.setId(id);
            clubRepository.save(club);
        } else
            return ResponseEntity.ok(new MessageResponse("Error: Id does not exist ["+id+"] -> cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Club record updated"));
    }


}
