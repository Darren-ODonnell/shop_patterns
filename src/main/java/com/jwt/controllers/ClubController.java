package com.jwt.controllers;

import com.jwt.models.Club;
import com.jwt.models.ClubModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.ClubService;
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
@RequestMapping("/club")
public class ClubController {
    public final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    // return all Clubs

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Club> list(){
        return clubService.list();
    }

    // return Club by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Club findById(@RequestParam("id")  Long id){
        return clubService.findById(id);
    }

    // return Club by name

    @GetMapping(value="/findByName/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  Club findByName(@ModelAttribute ClubModel clubModel) {
        return clubService.findByName(clubModel);
    }

    // add new Club

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@ModelAttribute ClubModel clubModel){
        return clubService.add(clubModel);
    }

    // edit/update a Club record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestParam("id") Long id,  @ModelAttribute ClubModel clubModel) {
        return clubService.update( id, clubModel);
    }

    // delete by id

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return clubService.deleteById(id);
    }
}
