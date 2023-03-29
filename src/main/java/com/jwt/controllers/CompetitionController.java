package com.jwt.controllers;

import com.jwt.models.Competition;
import com.jwt.models.CompetitionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.CompetitionService;
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
@RequestMapping({"/competition","/competitions"})
public class CompetitionController {
    CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // return all Competitions - done

    @GetMapping(value={"/","/list"} )
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody   List<Competition> list(){
        return competitionService.list();
    }


    // return Competition by id

    @GetMapping(value="/findById")
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody
    Competition findById(@RequestParam("id") Long id){
        return competitionService.findById(id);
    }

    // return Competition by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Competition> findByName(@ModelAttribute CompetitionModel competitionModel) {
        return competitionService.findByName(competitionModel);
    }

    // return Competition by name

    @GetMapping(value="/findBySeason")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Competition> findBySeason(@ModelAttribute CompetitionModel competitionModel) {
        return competitionService.findBySeason(competitionModel);
    }

    // add new Competition

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> add(@ModelAttribute CompetitionModel competitionModel){
        return competitionService.add( competitionModel);
    }

    // edit/update a competition record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> update( @ModelAttribute Competition competition){
        return competitionService.update(competition.getId(), competition);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody   List<Competition> delete(@ModelAttribute Competition  competition){
        return competitionService.delete(competition) ;
    }
}
