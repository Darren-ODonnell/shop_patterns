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

    // return all fellows - done

    @GetMapping(value={"/", "/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fellowship> list() {
        return fellowshipService.list();
    }

    // return fellow by id

    @GetMapping(value={"/findById","/player/findById","/manager/findById"})
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Fellowship findById(@RequestParam("id") Long id){
        return fellowshipService.findById(id);
    }

    // return fellow by email

    @GetMapping(value={"/findByEmail","/player/findByEmail","/manager/findByEmail"})
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Fellowship findByEmail(@RequestParam("email") String email){
        return fellowshipService.findByEmail(email);
    }

    // return fellow by firstname + lastname

    @GetMapping(value={"/findByFirstnameLastname","/player/findByFirstnameLastname","/manager/findByFirstnameLastname"})
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

    // add any fellow

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add( @RequestBody FellowshipModel fellowModel){
        return fellowshipService.add( fellowModel);
    }
    // add player fellow

    @PutMapping(value="/player/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> addPlayer( @ModelAttribute FellowshipModel fellowModel){
        fellowModel.setFellowType("Player");
        return fellowshipService.add( fellowModel);
    }
    // add manager fellow

    @PutMapping(value="/manager/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> addManager( @ModelAttribute FellowshipModel fellowModel){
        fellowModel.setFellowType("Manager");
        return fellowshipService.add( fellowModel);
    }

    // update any fellow

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update( @ModelAttribute Fellowship fellow){
        return fellowshipService.update(fellow.getId(), fellow);
    }

    // update player fellow

    @PostMapping(value="/player/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> updatePlayer( @ModelAttribute Fellowship fellow){
        fellow.setFellowType("Player");
        return fellowshipService.update(fellow.getId(), fellow);
    }
    // update manager fellow

    @PostMapping(value="/manager/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> updateManager( @ModelAttribute Fellowship fellow){
        fellow.setFellowType("Manager");
        return fellowshipService.update(fellow.getId(), fellow);
    }

    // delete any fellow

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fellowship>  delete(@ModelAttribute Fellowship fellow){
        return fellowshipService.delete(fellow);
    }

    // delete player fellow

    @DeleteMapping(value="/player/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Fellowship>  deletePlayer(@ModelAttribute Fellowship fellow){
        return fellowshipService.delete(fellow);
    }

    // delete manager fellow

    @DeleteMapping(value="/manager/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fellowship>  deleteManager(@ModelAttribute Fellowship fellow){
        return fellowshipService.delete(fellow);
    }

}
