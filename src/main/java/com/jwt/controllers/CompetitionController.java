package com.jwt.controllers;

import com.jwt.models.Competition;
import com.jwt.models.CompetitionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Darren O'Donnell
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);
    CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // return all Competitions - done

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody   List<Competition> list(){
        return competitionService.list();
    }

    // delete by id

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){

        return competitionService.deleteById(id) ;
    }

    // return Competition by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Optional<Competition> findById(@RequestParam("id") Long id){
        return competitionService.findById(id);
    }

    // return Competition by name

    @GetMapping(value="/findByName/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public @ResponseBody Competition findByName(@ModelAttribute CompetitionModel competitionModel) {

        return competitionService.findByName(competitionModel);
    }

    // add new Competition

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody  ResponseEntity<MessageResponse> add(@ModelAttribute CompetitionModel competitionModel){

        return competitionService.add( competitionModel);
    }

    // edit/update a competition record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update( @RequestParam("id") Long id, @ModelAttribute CompetitionModel competitionModel){

        return competitionService.update(id, competitionModel);
    }

}
