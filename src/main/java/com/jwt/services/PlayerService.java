package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if(!playerRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete player with id: "+id, MessageTypes.WARN));

        playerRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Player deleted with id: " + id, MessageTypes.INFO));
    }

    // return player by id

    public Player findById(Long id){
        if(playerRepository.findById(id).isEmpty())
            throw new NotFoundException("No PLayer found this this id: " + id);

        return playerRepository.findById(id).get();
    }

    // return player by firstname + lastname

    public Player findByFirstnameLastname(PlayerModel playerModel) {
        Optional<Player> player = playerRepository.findByFirstnameAndLastname(playerModel.getFirstname(), playerModel.getLastname());

        if(player.isEmpty()) {
            new MyMessageResponse("Player not found with Firstname: "+playerModel.getFirstname()+", and lastname: "+playerModel.getLastname(), MessageTypes.WARN);
            return new Player();
        }
        return player.orElse(new Player());
    }

    // return players with same lastname

    public  List<Player> findByLastname(PlayerModel playerModel) {
        Optional<List<Player>> players = playerRepository.findByLastname(playerModel.getLastname());
        if(players.isEmpty()) {
            new MyMessageResponse("Player not found with lastname: " + playerModel.getLastname(), MessageTypes.WARN);
            return new ArrayList<>();
        }
        return players.orElse(new ArrayList<>());
    }

    // return players with same firstname

    public List<Player> findByFirstname(PlayerModel playerModel) {
        Optional<List<Player>> players = playerRepository.findByFirstname(playerModel.getFirstname());
        if(players.isEmpty()) {
            new MyMessageResponse("Player not found with Firstname: "+playerModel.getFirstname(), MessageTypes.WARN);
        }
        return players.orElse(new ArrayList<>());
    }

    // add player

    public ResponseEntity<MessageResponse> add( PlayerModel playerModel){
        if(playerRepository.existsByFirstnameAndLastname(playerModel.getFirstname(), playerModel.getLastname()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Player already exists", MessageTypes.WARN));

        playerRepository.save(playerModel.translateModelToPlayer());
        return ResponseEntity.ok(new MyMessageResponse("New PLayer Added", MessageTypes.INFO));
    }

    // edit/update player

    public ResponseEntity<MessageResponse> update(Long id, PlayerModel playerModel){
        if(!playerRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Player with Id: ["+id+"] -> does not exist - cannot update record", MessageTypes.WARN));

        playerRepository.save(playerModel.translateModelToPlayer(id));
        return ResponseEntity.ok(new MyMessageResponse("Player record updated", MessageTypes.INFO));
    }

}
