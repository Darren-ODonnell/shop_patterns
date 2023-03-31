package com.jwt.controllers;


import com.jwt.models.Player;
import com.jwt.models.PlayerModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.ManagerService;
import com.jwt.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/player","/players"})
public class PlayerController {
    public final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // return all players - done

    @GetMapping(value={"/", "/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Player> list() {
        return playerService.list();
    }

    // return player by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Player findById(@RequestParam("id") Long id){
        return playerService.findById(id);
    }

    // return player by firstname + lastname

    @GetMapping(value="/findByFirstnameLastname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Player findByFirstnameLastname(@ModelAttribute PlayerModel playerModel ) {
        return playerService.findByFirstnameLastname(playerModel);
    }

    // return players with same lastname

    @GetMapping(value="/findByLastname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Player> findByLastname(@ModelAttribute PlayerModel playerModel) {
        return playerService.findByLastname(playerModel);
    }

    // return players with same firstname

    @GetMapping(value="/findByFirstname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Player> findByFirstname(@ModelAttribute PlayerModel playerModel) {
        return playerService.findByFirstname(playerModel);
    }

    // get by email address

    @GetMapping(value="/findByEmail")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Player findByEmail(@ModelAttribute PlayerModel playerModel) {
        return playerService.findByEmail(playerModel.getEmail());
    }

    // add player

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> add( @RequestBody PlayerModel playerModel){
        return playerService.add( playerModel);
    }

    // update player

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> update( @RequestBody Player player){
        return playerService.update(player.getId(), player);
    }


    // delete player

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Player>  delete(@RequestBody Player player){
        return playerService.delete(player);
    }
}
