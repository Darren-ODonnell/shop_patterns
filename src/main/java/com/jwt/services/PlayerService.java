package com.jwt.services;

import com.jwt.exceptions.NotFoundException;
import com.jwt.models.Player;
import com.jwt.models.PlayerModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // return all players - done

    public List<Player> list() {
        return playerRepository.findAll();
    }

    // delete player

    public ResponseEntity<MessageResponse> deleteById(Long id){
        logger.info("delete Player by id = "+id);
        if(playerRepository.existsById(id))
            playerRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete player with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Player deleted with id: " + id));
    }

    // return player by id

    public Player findById(Long id){
        if(playerRepository.findById(id).isPresent())
            return playerRepository.findById(id).get();
        else
            throw new NotFoundException("No PLayer found this this id: " + id);
    }

    // return player by firstname + lastname

    public Player findByFirstnameLastname(String firstname, String lastname) {

        return playerRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    // return players with same lastname

    public  List<Player> findByLastname(String lastname) {
        return playerRepository.findByLastname(lastname);
    }

    // return players with same firstname

    public List<Player> findByFirstname(String firstname) {
        return playerRepository.findByFirstname(firstname);
    }

    // add player

    public ResponseEntity<MessageResponse> add( PlayerModel playerModel){
        if(playerRepository.existsByFirstnameAndLastname(playerModel.getFirstname(), playerModel.getLastname()))
            return ResponseEntity.ok(new MessageResponse("Error: Player already exists"));
        else
            playerRepository.save(playerModel.translateModelToPlayer());

        return ResponseEntity.ok(new MessageResponse("New PLayer Added"));
    }

    // edit/update player

    public ResponseEntity<MessageResponse> update(Long id, PlayerModel playerModel){
        if(playerRepository.existsById(id)) {
            Player player = playerModel.translateModelToPlayer();
            player.setId(id);
            playerRepository.save(player);

        } else
            return ResponseEntity.ok(new MessageResponse("Error: Player with Id: ["+id+"] -> does not exist - cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Player record updated"));

    }

}
