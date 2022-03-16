package com.jwt.controllers;


import com.jwt.models.Player;
import com.jwt.models.PlayerModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Controller
@RequestMapping("/player")
public class PlayerController {
    public final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // return all players - done

    @GetMapping(value={"/", "/list"} )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Player> list() {
        return playerService.list();
    }

    // delete player

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
            return playerService.deleteById(id);
    }

    // return player by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Player findById(@RequestParam("id") Long id){
        return playerService.findById(id);
    }

    // return player by firstname + lastname

    @GetMapping(value="/findByFirstnameLastname/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Player findByFirstnameLastname(@ModelAttribute PlayerModel playerModel ) { //@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        return playerService.findByFirstnameLastname(playerModel);
    }

    // return players with same lastname

    @GetMapping(value="/findByLastname/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERTAOR') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Player> findByLastname(@ModelAttribute PlayerModel playerModel) {
        return playerService.findByLastname(playerModel);
    }

    // return players with same firstname

    @GetMapping(value="/findByFirstname/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Player> findByFirstname(@ModelAttribute PlayerModel playerModel) {
        return playerService.findByFirstname(playerModel);
    }

    // add player

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody  ResponseEntity<MessageResponse> add( @ModelAttribute PlayerModel playerModel){
        return playerService.add( playerModel);
    }

    // update player

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> update( @RequestParam("id") Long id, @ModelAttribute PlayerModel playerModel){
        return playerService.update(id, playerModel);
    }
}
