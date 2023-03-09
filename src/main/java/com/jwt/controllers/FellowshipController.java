package com.jwt.controllers;


import com.jwt.models.Fellowship;
import com.jwt.models.FellowshipModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.FellowshipService;
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
@RequestMapping({"/fellowship"})
public class FellowshipController {
    public final FellowshipService fellowshipService;

    @Autowired
    public FellowshipController(FellowshipService fellowService) {
        this.fellowshipService = fellowService;
    }
//
//    // return all fellows - done
//
    @GetMapping(value={"/", "/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fellowship> list() {
        return fellowshipService.list();
    }

    // return fellow by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Fellowship findById(@RequestParam("id") Long id){
        return fellowshipService.findById(id);
    }

    // return fellow by email

    @GetMapping(value="/findByEmail")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Fellowship findByEmail(@RequestParam("email") String email){
        return fellowshipService.findByEmail(email);
    }

    // return fellow by firstname + lastname

    @GetMapping(value="/findByFirstnameLastname")
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody Fellowship findByFirstnameLastname(@ModelAttribute FellowshipModel fellowModel ) {
        return fellowshipService.findByFirstnameLastname(fellowModel);
    }

    // return fellows with same lastname

    @GetMapping(value="/findByLastname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fellowship> findByLastname(@ModelAttribute FellowshipModel fellowModel) {
        return fellowshipService.findByLastname(fellowModel);
    }

    // return fellows with same firstname

    @GetMapping(value="/findByFirstname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fellowship> findByFirstname(@ModelAttribute FellowshipModel fellowModel) {
        return fellowshipService.findByFirstname(fellowModel);
    }

    // add fellow

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> add( @RequestBody FellowshipModel fellowModel){
        return fellowshipService.add( fellowModel);
    }

    // update fellow

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> update( @RequestBody Fellowship fellow){
        return fellowshipService.update(fellow.getId(), fellow);
    }


    // delete fellow

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Fellowship>  delete(@RequestBody Fellowship fellow){
        return fellowshipService.delete(fellow);
    }
}
