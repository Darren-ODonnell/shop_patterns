package com.jwt.controllers;

import com.jwt.models.Club;
import com.jwt.models.ClubModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.ClubService;
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
@RequestMapping({"/club","/clubs"})
public class ClubController {
    public final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    // return all Clubs

    @GetMapping(value={"/","/list"} )
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Club> list(){
        return clubService.list();
    }

    // return Club by id

    @GetMapping(value="/findById")
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody
    Club findById(@RequestParam("id")  Long id){
        return clubService.findById(id);
    }

    // return Club by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody  Club findByName(@ModelAttribute ClubModel clubModel) {
        return clubService.findByName(clubModel);
    }

    // add new Club

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> add(@RequestBody ClubModel clubModel){
        return clubService.add(clubModel);
    }

    // edit/update a Club record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> update(@ModelAttribute Club club) {
        return clubService.update( club.getId(), club );
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Club> delete(@ModelAttribute Club club){
        return clubService.delete(club);
    }
}
